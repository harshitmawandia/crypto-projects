How to use:
- Execute `make` to run the driver code.
- Execute `make demo` to run the demo code.

Note: For those who could not get `make` to work on their machines, they can alternatively use:
- `javac DriverClass.java` then `java DriverClass` to run the driver code.
- `javac DemoClass.java` then `java DemoClass` to run the demo code.

The student needs to only write code in *CRF.java* and *Signature.java* leaving other files untouched. DriverClass and DemoClass have been implemented for ease of understanding the starter code and for running basic tests on your implementations.

You have been provided with various test cases in the TestCases folder.
- Sample FindCollRandomizedAttempts.txt and FindCollRandomizedOutcome.txt for outputsize=4 are provided to you for your reference.
- SampleInput_sign.txt and SampleOutput_sign.txt shows the input and "one of the many possible" outputs for the Sign() function. The underlying signature scheme used in the starter code uses a random seed while generating the signatures and hence results in different signature values on each run.
- SampleInput_verify.txt and SampleOutput_verify.txt shows the input and output for the Verify() function. You may use these values to test your Verify() function independently.