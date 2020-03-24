package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object sequence
     
    /**
     * <p></p>
     */
    public static Object testCaseID
     
    /**
     * <p></p>
     */
    public static Object step
     
    /**
     * <p></p>
     */
    public static Object results
     
    /**
     * <p></p>
     */
    public static Object no
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            sequence = selectedVariables['sequence']
            testCaseID = selectedVariables['testCaseID']
            step = selectedVariables['step']
            results = selectedVariables['results']
            no = selectedVariables['no']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
