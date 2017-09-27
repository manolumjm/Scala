package listado;

/**
 * Created by manol on 07/03/2017.
 */
public class Empleado
{
    private String dni, nombre, apellidos, dirCorreo;
    private Division division;
    private Departamento departamento;


    public Empleado(String dni,String nombre, String apellidos, String dirCorreo)
    {
        this.dni = dni;
        this.nombre =  nombre;
        this.apellidos = apellidos;
        this.dirCorreo =  dirCorreo;
        this.division = Division.DIVNA;
        this.departamento = Departamento.DEPNA;

    }

    public String obtenerDNI()
    {
        return this.dni;
    }

    public void asignarDNI(String dni)
    {
        this.dni = dni;
    }

    // // Metodo para asignar el nombre
    public void asignarNombre(String nombre)
    {
        this.nombre = nombre;
    }

    // Metodo para acceder al nombre
    public String obtenerNombre() {
        return nombre;
    }

    // Metodo para asignar el apellido
    public void asignarPrimerApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    // Metodo para obtener apellidos
    public String obtenerApellidos() {
        return apellidos;
    }

    // Metodo para asignar el correo
    public void asignarCorreo(String correo) {
        this.dirCorreo = correo;
    }

    // Metodo para obtener el correo
    public String obtenerCorreo() {
        return dirCorreo;
    }

    public void asignarDivision(Division division)
    {
        this.division = division;
    }

    public void asignarDepartamento(Departamento departamento)
    {
        this.departamento = departamento;
    }

    public Departamento obtenerDepartamento()
    {
        return this.departamento;
    }

    public Division obtenerDivision()
    {
        return this.division;
    }

    // Metodo toString
    @Override
    public String toString()
    {
        return String.format("\n%-8s %-8s %-8s %-8s %-8s %-8s",
                obtenerDNI(), obtenerNombre(), obtenerApellidos(),
                obtenerCorreo(),obtenerDepartamento(), obtenerDivision());
    }




}
