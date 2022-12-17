package util;
/**
 * @author Jon Maneiro García
 */
public class funcionesComunes {

    /**
     * Se comprueba si la variable suministrada se puede convertir a long
     *
     * @param check String a comprobar
     * @return boolean yes/no
     */
    public static boolean isLong(String check) {
        try {
            Long.parseLong(check);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Se comprueba si la variable suministrada se puede convertir a int
     *
     * @param check String a comprobar
     * @return boolean yes/no
     */
    public static boolean isInt(String check) {
        try {
            Integer.parseInt(check);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Se comprueba si la variable suministrada se puede convertir a double
     * @param check String a comprobar
     * @return boolean yes/no
     */
    public static boolean isDouble(String check){
        try{
            Double.parseDouble(check);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

}
