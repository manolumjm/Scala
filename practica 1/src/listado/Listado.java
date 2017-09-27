package listado;

import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by manol on 07/03/2017.
 */
public class Listado
{
    /**
     * Dato miembro para almacenar a los empleados como diccionario
     * con pares tipo (clave - valor) con el siguiente contenido:
     * <dni - listado.Empleado>
     */
    private Map<String, Empleado> lista;

    public Listado(String nombreArchivo) throws Exception
    {
        // Creación del diccionario sobre el dato miembro lista
        this.lista = new HashMap<String, Empleado>();

        // Obtenemos las lineas del archivo que pasen por argumento
        Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));

        // Para cada línea del archivo almacenamos el objeto de la clase empleado
        // en el diccionario Map<String, listado.Empleado>
        this.lista = lineas.map(linea -> {
            return crearEmpleado(linea);
        }).collect(Collectors.toMap(empleado -> empleado.obtenerDNI(), Function.identity()));


    }

    private Empleado crearEmpleado(String linea)
    {
        // Se define el patron para las comas que hacen de separador
        Pattern pattern = Pattern.compile("(,)");

        // Se procesa la linea
        List<String> infos = pattern.splitAsStream(linea).collect(Collectors.toList());

        return (new Empleado(infos.get(0),infos.get(1),infos.get(2),infos.get(3)));
    }

    public void cargarArchivoAsignacionDivision(String nombreArchivo) throws Exception
    {
        // Obtenemos flujo de lineas del archivo
        Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));

        // Encontramos la primera linea del archivo. Por ejemplo: DIVSW
        String division = lineas.findFirst().orElseGet(null);

        // Aqui obtenemos las lineas con los dnis.
        // Obtenemos de nuevo el flujo y con skip bajamos dos líneas para empezar a leer los dnis
        Files.lines(Paths.get(nombreArchivo)).skip(2).forEach(
                linea -> procesarAsignacionDivision(linea,division));
    }

    private void procesarAsignacionDivision(String dni, String cadDivision)
    {
        // Buscar empleado con dni = dni
        Predicate<Division> condicion = division -> division.name().equals(cadDivision);

        // Asignar al objeto la division
        Division divisionRes = Arrays.stream(Division.values()).filter(condicion).findFirst().get();
        this.lista.get(dni).asignarDivision(divisionRes);
    }

    public void cargarArchivoAsignacionDepartamento(String nombreArchivo) throws Exception
    {
        // Obtenemos flujo de lineas del archivo
        Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));

        // Encontramos la primera linea del archivo. Por ejemplo: DIVSW
        String departamento = lineas.findFirst().orElseGet(null);

        // Aqui obtenemos las lineas con los dnis.
        // Obtenemos de nuevo el flujo y con skip bajamos dos líneas para empezar a leer los dnis
        Files.lines(Paths.get(nombreArchivo)).skip(2).forEach(
                linea -> procesarAsignacionDepartamento(linea,departamento));
    }

    private void procesarAsignacionDepartamento(String dni, String cadDepartamento)
    {
        // Buscar empleado con dni = dni
        Predicate<Departamento> condicion = departamento -> departamento.name().equals(cadDepartamento);

        // Asignar al objeto la division
        Departamento departamentoRes = Arrays.stream(Departamento.values()).filter(condicion).findFirst().get();
        this.lista.get(dni).asignarDepartamento(departamentoRes);
    }

    public void listarEmpleados(){

        this.lista.values().stream().forEach(System.out::println);
    }


    // Funcion que devuelve los datos de todos los empleados
    @Override
    public String toString()
    {
        return lista.values().toString();
    }

    // Funcion para devolver el numero de empleados que han sido leidos de los ficheros
    public int obtenerNumeroEmpleados()
    {
        return lista.values().size();
    }



    // Contadores asignados a cada division y departamento. Este metodo se apoya en obtenerContadoresDepartamento
    public Map<Division, Map<Departamento, Long>> obtenerContadoresDivisionDepartamento()
    {
        // Creamos el map que devolveremos
        Map<Division, Map<Departamento, Long>> resultado = new HashMap<>();

        // Le hacemos un flujo al conjunto de divisiones que existen
        Stream<Division> divisiones = Arrays.stream(Division.values());

        // Recorremos las diferentes divisiones para asignarles los contadores de empleados asignados
        // a cada departamento
        divisiones.forEach(a -> resultado.put(a, obtenerContadoresDepartamento(a)));

        return resultado;
    }

    // Contadores de empleados asignados a cada departamento
    public Map<Departamento, Long> obtenerContadoresDepartamento(Division division)
    {
        TreeMap<Departamento, Long> collect = lista.values().stream().
                filter(div -> div.obtenerDivision().equals(division)).
                collect(Collectors.groupingBy(Empleado::obtenerDepartamento,
                TreeMap::new, Collectors.counting()));

        return collect;
    }


    /**
     * Método para buscar los empleados sin division asignada: es decir,
     * en el dato miembro division tendran el valor DIVNA
     * @return
     */
    public List<Empleado> buscarEmpleadosSinDivision()
    {
        return lista.values().stream().
                filter(empleado -> empleado.obtenerDivision().equals(Division.DIVNA)).
                collect(Collectors.toList());
    }


    /**
     * Método para buscar empleados con division asignada (no es DIVNA)
     * pero sin departamento: el valor del dato miembro departamento es
     * (DEPNA)
     * @return
     */
    public List<Empleado> buscarEmpleadosConDivisionSinDepartamento()
    {
        return lista.values().stream().
                filter(empleado -> empleado.obtenerDepartamento().equals(Departamento.DEPNA) &&
                        (!empleado.obtenerDivision().equals(Division.DIVNA)) ).
                collect(Collectors.toList());
    }


    /**
     * Método para buscar todos los empleados no asignados a departamento
     * que pertenezcan a una determinada division
     * @param divisionObjetivo division de interes
     * @return lista de empleados sin departamento asignado
     */
    public List<Empleado> buscarEmpleadosSinDepartamento(Division divisionObjetivo)
    {
        return lista.values().stream().filter(empleado -> empleado.obtenerDivision().equals(divisionObjetivo) &&
                empleado.obtenerDepartamento().equals(Departamento.DEPNA)).
                collect(Collectors.toList());
    }

    /**
     * Metodo para determinar si hay dnis repetidos
     * @return
     */
    public boolean hayDnisRepetidos()
    {
        return lista.values().stream().collect(Collectors.groupingBy(Empleado::obtenerDNI)).values()
                .stream().anyMatch(dni -> dni.size() > 1);
    }

    /**
     * Metodo para obtener una lista de dnis repetidos, junto con la
     * lista de trabajadores asociados a cada dni repetido (en caso de
     * haberlos)
     * @return
     */

    public Map<String,List<Empleado>> obtenerDnisRepetidos()
    {
        return lista.values().stream().
                collect(Collectors.groupingBy(Empleado::obtenerDNI)).
                entrySet().stream().
                filter(map -> map.getValue().size() > 1).collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));
    }

    /**
     * Metodo para determinar si hay correos repetidos
     * @return
     */
    public boolean hayCorreosRepetidos()
    {
        return lista.values().stream().collect(Collectors.groupingBy(Empleado::obtenerCorreo)).values()
                .stream().anyMatch(email -> email.size() > 1);
    }

    /**
     * Metodo para obtener una lista de dnis repetidos, junto con la
     * lista de trabajadores asociados a cada dni repetido (en caso de
     * haberlos)
     * @return
     */
    public Map<String,List<Empleado>> obtenerCorreosRepetidos()
    {
        return lista.values().stream().collect(Collectors.groupingBy(Empleado::obtenerCorreo)).entrySet()
                .stream().filter(map -> map.getValue().size() > 1).collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));
    }
}
