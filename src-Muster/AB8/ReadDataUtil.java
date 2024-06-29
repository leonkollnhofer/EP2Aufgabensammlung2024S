package AB8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadDataUtil {

    /**
     * Reads the position and velocity vector on the specified 'day' from the file with the
     * specified 'path', and sets position and current velocity of 'b' accordingly. If
     * successful the method returns 'true'. If the specified 'day' was not found in the file,
     * 'b' is unchanged and the method returns 'false'.
     * The file format is validated before reading the state.
     * Lines before the line "$$SOE" and after the line "$$EOE" are ignored. Each line of the
     * file between the line "$$SOE" and the line "$$EOE" is required to have the following format:
     * JDTDB, TIME, X, Y, Z, VX, VY, VZ,
     * where JDTDB is interpretable as a 'double' value, TIME is a string and X, Y, Z, VX, VY and
     * VZ are interpretable as 'double' values. JDTDB can be ignored. The character ',' must only
     * be used as field separator and at the end of the line.
     * If the file is not found, an exception of type 'StateFileNotFoundException' is thrown. If
     * it does not comply with the format described above, the method throws an exception of type
     * 'StateFileFormatException'. Both exceptions are subtypes of 'IOException'.
     *
     * @param b the body for which the state has to be set, b != null.
     * @param path the path to the text file with the data of the body, path != null.
     * @param day a string with the date of the body's state in the format YYYY-MM-DD, day != null.
     * @return 'true' if the state was set successfully or 'false' if the specified 'day'
     *      was not found in the file.
     * @throws IOException an exception indicating a format error or that the file was not found.
     */

    public static boolean readConfiguration(Body b, String path, String day) throws IOException {

        //TODO: change implementation according to AB8
        // (method should throw exceptions in case of errors).
        BufferedReader in = null;
        boolean found = false;

        try {

            Reader r = new FileReader(path);
            in = new BufferedReader(r);
            in.close();

            r = new FileReader(path);
            in = new BufferedReader(r);

            String line;
            while ((line = in.readLine()) != null && !line.equals("$$SOE")) ;

            if (line == null) {
                throw new StateFileFormatException("$$SOE not found!");
            }

            while ((line = in.readLine()) != null && !line.equals("$$EOE")) {
                String[] fields = line.split(",");
                boolean checkPassed = false;

                Vector3 position;
                Vector3 velocity;
                if (fields.length == 8) {
                    position = new Vector3(Double.parseDouble(fields[2]),
                            Double.parseDouble(fields[3]),
                            Double.parseDouble(fields[4]));
                    velocity = new Vector3(Double.parseDouble(fields[5]),
                            Double.parseDouble(fields[6]),
                            Double.parseDouble(fields[7]));
                    checkPassed = true;

                    if (fields[1].contains(day)) {
                        found = true;
                        //TODO: activate statement:
                        b.setState(position.times(1000), velocity.times(1000));
                    }

                }
                if (!checkPassed) {
                    throw new StateFileFormatException("does not have required format");
                }
            }

        } catch (java.io.FileNotFoundException e) {
            throw new StateFileNotFoundException(e.getMessage());
        } catch (java.lang.NumberFormatException e) {
            throw new StateFileFormatException("does not have required format");
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return found;
    }
}

