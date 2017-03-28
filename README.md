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

Sending actions to the webpage is done by calling methods on the instance of the WebDriver object that is connected to the page. 

```java
driver.findElement(By.id("filter")).sendKeys("2002");
```
### Explain shortly about the DOM, and how you have read/manipulated DOM-elements in your test 

The DOM is the markup that creates the entire structure of an HTML page. It's a collection of tags that function as containers for other tags or for content to be displayed on the webpage. These tags can be specified using CSS, and be assigning names, IDs and classes.

I have read and manipulated the DOM in my tests by using the WebDriver methods that Selenium provides. Meaning that I have told the WebDriver to load the webpage, and then telling the driver to interact with different elements in the DOM through IDs etc.

### Explain how (and why it was necessary) you have solved "waiting" problems in your test

I used the code given in our first Selenium Example, which tells the WebDriver instance to wait either a set number of seconds, or for a condition to be fulfilled. The condition in this case is apply(), which means the page being fully loaded.

```java
(new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                
            }
        });
```

This code did however not solve the issue of the page loading before the JavaScript, meaning that the code above wouldn't work. To fix this issue, I implemented WebDriverWait, which waited for an HTML element to appear, which containts a specific string.

```java
WebElement wait = (new WebDriverWait(driver, 10))
           .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '" + elementToWatch + "')]")));
```
















