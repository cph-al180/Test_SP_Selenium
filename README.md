# Test_SP_Selenium

### Discuss Pros and Cons with manual versus automated tests

#### Manual:
##### Pros:
- More flexible than automated tests.
- Shorter startup period.
- Can be used as exploratory testing.
##### Cons:
- Effeciency issues. 
- More time consuming.
- For large systems it can require a full integration test, in order to test a single component.
- Human error.

#### Automated:
##### Pros:
- Efficient maintainability.
- Easy to unit test small/specific components of code.
- Faster response to new code implementations.
##### Cons:
- High overhead cost, depending on testing framework.

### Explain about the Test Pyramid and whether this exercise supported the ideas in the "pyramid"
The Test Pyramid explains how much focus should be given to different types and layers of testing, depending on the cost of their operations. The Pyramid suggests that high-level end-to-end tests on UI are costly, and that middle-level API tests are less costly, but still considerable, and that low-level unit tests are the cheapest. Because of this, the amount of tests should be equal to the costs mentioned above, meaning that there should be fewest UI tests, and so on.
The performed exercise does not support the ideas of the pyramid, since there was only performed high-level UI tests.

### Discuss some of the problems with automated GUI tests and what makes such tests "vulnerable"
Automated GUI tests have the requirement of running in an actual browser. They can encounter race condition-like issues in attempting to fulfill actions before the page has fully loaded the required data. The browsers can run into compatibility issues. The maintainability of these kinds of tests and also be very costly, since changes to the HTML can cause the tests to fail, meaning that they have to be reconfigured.

### Demonstrate details in how to create a Selenium Test using the code for the exercise

Selenium tests are done by connection to a webpage, using the WebDriver object, and then using Selenium's Java Library to send commands through the WebDriver to the webpage.

```java
driver = new ChromeDriver();
driver.get("localhost:3000");
```








