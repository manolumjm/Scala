package test; /**
 * Created by manol on 07/03/2017.
 */
import listado.Departamento;
import listado.Division;
import listado.Empleado;
import listado.Listado;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

//import listado.listado.Listado;
//import listado.listado.Division;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Práctica 1 NTP
 */
public class ListadoTest
{
    private static Listado listado;

    /**
     * Codigo a ejecutar antes de realizar las llamadas a los métodos
     * de la clase; incluso antes de la propia instanciación de la
     * clase. Por eso el método debe ser estatico
     */
    @BeforeClass
    public static void inicializacion() throws Exception
    {
        System.out.println("Metodo inicializacion conjunto pruebas");
        // Se genera el listado de empleados
        try
        {
            listado = new Listado("./data/datos.txt");
        }
        catch(IOException e)
        {
            System.out.println("Error en lectura de archivo de datos");
        };

        // Una vez disponibles los empleados se leen las listas
        // de asignaciones de empleados a cada grupo de las diferentes
        // asignaturas consideradas
        try
        {
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVNA.txt");
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVID.txt");
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVSW.txt");
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVHW.txt");
            listado.cargarArchivoAsignacionDivision("./data/asignacionDIVSER.txt");
            listado.cargarArchivoAsignacionDepartamento("./data/asignacionDEPNA.txt");
            listado.cargarArchivoAsignacionDepartamento("./data/asignacionDEPSB.txt");
            listado.cargarArchivoAsignacionDepartamento("./data/asignacionDEPSM.txt");
            listado.cargarArchivoAsignacionDepartamento("./data/asignacionDEPSA.txt");
        } catch (IOException e)
        {
            System.out.println("Error en lectura de archivos de asignacion");
        }
        System.out.println();

        //listado.listarEmpleados();
        //System.out.println(listado);
    }

    /**
     * Test para comprobar que se ha leido de forma correcta la
     * informacion de los empleados (al menos que el listado contiene
     * datos de 100 empleados)
     * @throws Exception
     */
    @Test
    public void testConstruccionListado() throws Exception{
        assert(listado.obtenerNumeroEmpleados() == 1000);
    }

    /**
     * Test del procedimiento de asignacion de grupos procesando
     * los archivos de asignacion. Tambien implica la prueba de
     * busqueda de empleados sin grupo asignado en alguna asignatura
     * @throws Exception
     */
    @Test
    public void testCargarArchivosAsignacion() throws Exception {
        // Se obtienen los empleados no asignados a cada asignatura
        // y se comprueba su valor
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVNA).size() == 49);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVID).size() == 54);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVSW).size() == 42);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVHW).size() == 44);
        assert(listado.buscarEmpleadosSinDepartamento(Division.DIVSER).size() == 49);
    }



    @Test
    public void testObtenerContadoresDepartamentosDIVSER() {
        // Se obtienen los contadores para la asignatura ES
        Map<Departamento, Long> contadores = listado.obtenerContadoresDepartamento(
                Division.DIVSER);
        contadores.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadores.get(key)));
        // Se comprueba que los valores son DEPNA = 49, DEPSB = 48, DEPSM = 53, DEPSA = 41
        Long contadoresReferencia[] = {49L, 48L, 53L, 41L};
        Long contadoresCalculados[] = new Long[4];
        assertArrayEquals(contadores.values().toArray(contadoresCalculados),
                contadoresReferencia);
    }

    @Test
    public void testObtenerContadoresDepartamentosDIVSW(){
        // Se obtienen los contadores para la asignatura ES
        Map<Departamento, Long> contadores = listado.obtenerContadoresDepartamento(
                Division.DIVSW);
        contadores.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadores.get(key)));
        // Se comprueba que los valores son DEPNA = 42, DEPSB = 52, DEPSM = 45, DEPSA = 53
        Long contadoresReferencia[]={42L,52L,45L,53L};
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadores.values().toArray(contadoresCalculados),
                contadoresReferencia);
    }

    @Test
    public void testObtenerContadoresDepartamentosDIVHW(){
        // Se obtienen los contadores para la asignatura ES
        Map<Departamento, Long> contadores = listado.obtenerContadoresDepartamento(
                Division.DIVHW);
        contadores.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadores.get(key)));
        // Se comprueba que los valores son DEPNA = 44, DEPSB = 43, DEPSM = 67, DEPSA = 62
        Long contadoresReferencia[]={44L,43L,67L,62L};
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadores.values().toArray(contadoresCalculados),
                contadoresReferencia);
    }

    @Test
    public void testObtenerContadoresDepartamentosDIVID(){
        // Se obtienen los contadores para la asignatura ES
        Map<Departamento, Long> contadores = listado.obtenerContadoresDepartamento(
                Division.DIVID);
        contadores.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadores.get(key)));
        // Se comprueba que los valores son DEPNA = 54, DEPSB = 49, DEPSM = 42, DEPSA = 43
        Long contadoresReferencia[]={54L,49L,42L,43L};
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadores.values().toArray(contadoresCalculados),
                contadoresReferencia);
    }

    @Test
    public void testObtenerContadoresDepartamentosDIVNA(){
        // Se obtienen los contadores para la asignatura ES
        Map<Departamento, Long> contadores = listado.obtenerContadoresDepartamento(
                Division.DIVNA);
        contadores.keySet().stream().forEach(key -> System.out.println(
                key.toString() + "- " + contadores.get(key)));
        // Se comprueba que los valores son DEPNA = 49, DEPSB = 53, DEPSM = 53, DEPSA = 58
        Long contadoresReferencia[]={49L,53L,53L,58L};
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadores.values().toArray(contadoresCalculados),
                contadoresReferencia);
    }

    /**
     * Prueba del procedimiento general de obtencion de contadores
     * para todas las asignaturas
     * @throws Exception
     */
    @Test
    public void testObtenerContadoresDivisionDepartamento() throws Exception {
        // Se obtienen los contadores para todos los grupos
        Map<listado.Division, Map<listado.Departamento, Long>> contadores =
                listado.obtenerContadoresDivisionDepartamento();

        // Se comprueban los valores obtenenidos con los valores por referencia
        Long contadoresReferenciaNA[]={49L,53L,53L,58L};
        Long contadoresReferenciaID[]={54L,49L,42L,43L};
        Long contadoresReferenciaHW[]={44L,43L,67L,62L};
        Long contadoresReferenciaSW[]={42L,52L,45L,53L};
        Long contadoresReferenciaSER[]={49L,48L,53L,41L};

        // Se comprueban los resultado del metodo con los de referencia
        Long contadoresCalculados[]=new Long[4];
        assertArrayEquals(contadores.get(Division.DIVNA).values().
                toArray(contadoresCalculados),contadoresReferenciaNA);
        assertArrayEquals(contadores.get(Division.DIVID).values().
                toArray(contadoresCalculados),contadoresReferenciaID);
        assertArrayEquals(contadores.get(Division.DIVHW).values().
                toArray(contadoresCalculados),contadoresReferenciaHW);
        assertArrayEquals(contadores.get(Division.DIVSW).values().
                toArray(contadoresCalculados),contadoresReferenciaSW);
        assertArrayEquals(contadores.get(Division.DIVSER).values().
                toArray(contadoresCalculados),contadoresReferenciaSER);
    }

    // Aqui habria que completar los casos de prueba para el resto de
    // metodos a ofrecer por la clase listado.Listado

    /**
     * Test que comprueba que el numero de empleados sin division es el correcto
     * @throws Exception
     */
    @Test
    public void testObtenerEmpleadosSinDivision() throws Exception {
        // Se obtienen los empleados sin Division
        // y se comprueba su valor
        assert(listado.buscarEmpleadosSinDivision().size() == 213);
    }

    /**
     * Test que comprueba que el numero de empleados con division
     *  pero sin Departamento es el correcto
     *
     * @throws Exception
     */
    @Test
    public void testObtenerEmpleadosConDivisionSinDepartamento() throws Exception {
        //238-49=189 => ya que 238 son los empleados sin departamentos asociados
        //y 49 el numero de empleados sin division asociado
        assert(listado.buscarEmpleadosConDivisionSinDepartamento().size() == 189);
    }

    /**
     * Test que comprueba que existen correos repetidos
     * @throws Exception
     */
    @Test
    public void testComprobarSiExistenCorreosRepetidos() throws Exception {
        assertTrue(listado.hayCorreosRepetidos());
    }

    /**
     * Test que comprueba para cada correo, el numero de empleados que lo repiten
     */
    @Test
    public void testObtenerContadoresCorreosRepetidos() {
        // Se obtienen los correos repetidos con la lista de empleados que la repiten
        Map<String,List<Empleado>> contadores = listado.obtenerCorreosRepetidos();

        Map<String, Integer> cantidadRepetida = contadores.entrySet().stream().collect(Collectors.toMap(map ->
                map.getKey(), map -> map.getValue().size()));


        contadores.forEach((email, empleados) -> {
            System.out.println();
            System.out.println(email);
            empleados.forEach(System.out::println);
        });


        Integer contadoresReferencia[] = {2, 2, 2, 3, 2, 2, 2, 2};
        Integer contadoresCalculados[] = new Integer[8];

        System.out.println(cantidadRepetida.keySet());
        assertArrayEquals(cantidadRepetida.values().toArray(contadoresCalculados),
                contadoresReferencia);
    }

    /**
     * Test que comprueba que existen dni repetidos
     * @throws Exception
     */
    @Test
    public void testComprobarSiNoExistenDNIRepetidos() throws Exception {
        assertFalse(listado.hayDnisRepetidos());
    }

    /**
     * Test que comprueba para cada dni, el numero de empleados que lo repiten
     */
    @Test
    public void testObtenerContadoresDNIRepetidos() {
        // Se obtienen los correos repetidos con la lista de empleados que la repiten
        Map<String,List<Empleado>> contadores = listado.obtenerDnisRepetidos();

        Map<String, Integer> cantidadRepetida = contadores.entrySet().stream().collect(Collectors.toMap(map ->
                map.getKey(), map -> map.getValue().size()));

        contadores.forEach((departamento, empleados) -> {
            System.out.println();
            System.out.println(departamento);
            empleados.forEach(System.out::println);
        });


        Integer contadoresReferencia[] = {};
        Integer contadoresCalculados[] = new Integer[0];

        System.out.println(cantidadRepetida.keySet());
        assertArrayEquals(cantidadRepetida.values().toArray(contadoresCalculados),
                contadoresReferencia);
    }

}
