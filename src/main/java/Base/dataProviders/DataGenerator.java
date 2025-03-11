package Base.dataProviders;

import Base.DTOs.SCDTO;
import Base.Resources.Log;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataGenerator {

    private static final Log logger = new Log(DataGenerator.class);

    @DataProvider(name = "testDataProvider")
    public static Object[][] testDataProvider(ITestContext context, ITestNGMethod testMethod) throws IOException {

        String currentDirectory = System.getProperty("user.dir");

        // Retrieve the current test method
        String testName = testMethod.getMethodName();
        Class<?> dtoClass = testMethod.getConstructorOrMethod().getMethod().getParameterTypes()[0];

        // Log for debugging
        logger.info("Method: {}, DTO: {}", testName, dtoClass);

        // Determine JSON file path
        String jsonFilePath = getJsonFilePathForDto(dtoClass, currentDirectory);
        Map<String, Object> testDataMap = loadTestData(jsonFilePath);

        // Check if there is test data for the test method
        if (testDataMap.containsKey(testName)) {
            Object testDataObject = testDataMap.get(testName);

            // Convert the test data object to a list of DTOs (regardless if it's a single or multiple objects)
            List<?> testDataList = convertToList(testDataObject, dtoClass);

            // Return each item in the list as a separate test data invocation
            Object[][] testDataArray = new Object[testDataList.size()][1];
            for (int i = 0; i < testDataList.size(); i++) {
                testDataArray[i][0] = testDataList.get(i);
            }

            return testDataArray;
        }

        throw new RuntimeException("No matching test data found for test method: " + testName);
    }

    // Determines the correct JSON file path based on the DTO type
    private static String getJsonFilePathForDto(Class<?> dtoClass, String currentDirectory) {
        String jsonFilePath;
        if (dtoClass.equals(SCDTO.class)) {
            jsonFilePath = currentDirectory + "/src/main/java/Base/dataProviders/ScTestData.json";
        } else {
            logger.error("Unsupported DTO type: {}", dtoClass.getSimpleName());
            throw new RuntimeException("Unsupported DTO type: " + dtoClass.getSimpleName());
        }
        logger.info("Loading data from JSON file: {}", jsonFilePath);

        return jsonFilePath;
    }

    // Generic method to load raw test data from JSON
    public static Map<String, Object> loadTestData(String filePath) throws IOException {
        logger.info("Loading test data from file: {}", filePath);
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read JSON as a map of objects
            Map<String, Object> rawData = mapper.readValue(new File(filePath), new TypeReference<Map<String, Object>>() {
            });
            logger.info("Test data loaded successfully from: {}", filePath);
            return rawData;

        } catch (IOException e) {
            logger.error("Error reading test data from file: {}", filePath, e);
            throw e;
        }
    }

    // Convert the test data object to a list of DTOs
    private static <T> List<T> convertToList(Object testDataObject, Class<T> dtoClass) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> testDataList = new ArrayList<>();

        if (testDataObject instanceof List) {
            // Handle case where testDataObject is already a list
            for (Object obj : (List<?>) testDataObject) {
                T dto = mapper.convertValue(obj, dtoClass);
                testDataList.add(dto);
            }
        } else {
            // Handle case where testDataObject is a single DTO object
            T dto = mapper.convertValue(testDataObject, dtoClass);
            testDataList.add(dto);
        }

        return testDataList;
    }
}
