import dzmitry.klokau.testing.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class CalculatorTest {


    @ParameterizedTest
    @CsvFileSource(resources = "smoke_params.csv")
    public void calculatorMegaTest(String attributes, String param1, String param2, String expectedRes) throws InvocationTargetException, IllegalAccessException {

        Method[] methods = Calculator.class.getMethods();
        for (Method method : methods) {
            String returnedType = method.getReturnType().getName();
//            Type returnedType1 = method.getReturnType();
            String name = method.getName();

            Class[] paramTypes = method.getParameterTypes();
            if (name.equals("sum") | name.equals("subtraction") | name.equals("multiplication") | name.equals("division")) {
                String parameter1 = paramTypes[0].getName();
                String parameter2 = paramTypes[1].getName();
                String methodAttrs;
                Object expectedResult = null;
                Object inputParam1 = null;
                Object inputParam2 = null;
                methodAttrs = returnedType + "_" + name + "_" + parameter1 + "_" + parameter2;

                if (methodAttrs.equals(attributes)) {
                    switch (returnedType) {
                        case "int":
                            expectedResult = Integer.parseInt(expectedRes);
                            break;
                        case "float":
                            expectedResult = Float.parseFloat(expectedRes);
                            break;
                        case "double":
                            expectedResult = Double.parseDouble(expectedRes);
                            break;
                        case "long":
                            expectedResult = Long.parseLong(expectedRes);
                            break;
                    }
                    switch (parameter1) {
                        case "int":
                            inputParam1 = Integer.parseInt(param1);
                            break;
                        case "short":
                            inputParam1 = Short.parseShort(param1);
                            break;
                        case "long":
                            inputParam1 = Long.parseLong(param1);
                            break;
                        case "byte":
                            inputParam1 = Byte.parseByte(param1);
                            break;
                        case "float":
                            inputParam1 = Float.parseFloat(param1);
                            break;
                        case "double":
                            inputParam1 = Double.parseDouble(param1);
                            break;
                        case "java.lang.String":
                            inputParam1 = param1;
                            break;
                        case "char":
                            inputParam1 =(char)Integer.parseInt(param1);
                            break;
                    }
                    switch (parameter2) {
                        case "int":
                            inputParam2 = Integer.parseInt(param2);
                            break;
                        case "short":
                            inputParam2 = Short.parseShort(param2);
                            break;
                        case "long":
                            inputParam2 = Long.parseLong(param2);
                            break;
                        case "byte":
                            inputParam2 = Byte.parseByte(param2);
                            break;
                        case "float":
                            inputParam2 = Float.parseFloat(param2);
                            break;
                        case "double":
                            inputParam2 = Double.parseDouble(param2);
                            break;
                        case "java.lang.String":
                            inputParam2 = param2;
                            break;
                        case "char":
                            inputParam2 =(char)Integer.parseInt(param2);
                            break;
                    }
                    Object[] arguments = new Object[]{inputParam1, inputParam2};
                    Object actualResult = method.invoke(new Calculator(), arguments);
                    Assertions.assertEquals(expectedResult.toString(), actualResult.toString());
                }
            }
        }
    }
}


