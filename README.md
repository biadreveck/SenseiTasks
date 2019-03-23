SenseiTasks
--------------

What we mainly wanted to bring here is the approach we used to make sure the code is testable, decoupled and maintainable. The is specially relevant for long-term projects and growing environments.

* **This is following a Clean Architecture approach**  
Notice that the layers communicates all the chain downstream before coming back and inner layers doesn't know about outer layers. All the business logic rules are inside Use Cases. ViewModel is only updating the View. Repository is just passing what the domain layer expects. These guys might be break into separated modules.

* **These are testable classes**  
All dependencies are being injected by constructor and hence allowing a very good level of unit testing. Notice the `Executors` class which play a major role in this example since I'll be able to use ANY `Executor` I want when writing tests. And what I want to run synchronously code that is asynchronous. This is key for a reliable test suite in this project.

* **The repository contract belongs to the domain layer**  
Interesting that the data layer Repository implementation is following the contract defined in domain layer Repository class. The data layer is aware of the most inner domain layer but the domain layer is not aware of the existence of the data layer. This will be even more noticeable when separating into modules.

* **There are unit tests in the whole Use Case**  
There is not mock classes in GetTaskListScenario. This is real benefit the project will earn. The whole use case, where important business rules lies on, will be completely testable within a single unit test, running locally on the JVM. Thanks MockWebServer! The only fake thing here is the response from the real lightweight server that is fired up with the this test.

* **I need to mention the Transformations**  
This is extra overhead. That's a trade-off: I get more thinner domain models in exchange of having an extra same model in the data layer along with its corresponding transformation.
