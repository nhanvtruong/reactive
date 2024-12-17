# Spring Reactive Demo

**Disclaimer**: These documents are compiled from various sources on the internet, carefully curated
to provide the most coherent and sensible information. However, they are not an absolute source of
truth. If you have any concerns, please verify the information carefully.

## 🤓 **_Nerdy Section, we talked about theory here_**

### Typical Blocking Request Handling

First, let's consider this scenario:

1. A client makes a request to the backend application. The Tomcat server assigns a thread to handle
   the request.
2. The backend application performs some business logic.
3. The backend application sends a request to the database to fetch the required data.
4. The backend application waits for the response from the database.
5. The database processes the request and returns the data to the backend application.

In this scenario, at every I/O step, the thread has to wait for some kind of processing. Let's say
the server has a thread pool that can serve 200 concurrent users, so there will be 200 threads in
use, all waiting for the database operations to complete. Consequently, the 201st user will have to
wait for a thread to complete its job before their request can be processed.

![201st user waiting for his request to be picked up](https://preview.redd.it/4o3gfaub95781.png?width=640&crop=smart&auto=webp&s=a8f6da6314730390bcd4b4dd50ef6c52122bcc7a)

### Non-Blocking Request Handling in Spring Reactive

At this point, we do not want to wait for the database operation to complete. Instead, we want the
thread to go back and serve another request as soon as possible.

For this to happen, the thread will not wait after sending the request to the database. It will
immediately become available to handle the next client request.

Once the database operation is completed, it will make a call-back. The earlier thread or any
available thread will be assigned to fetch the data from the database and send it back to the
client.

This behavior is known as the event-loop mechanism , which is widely used by other famous
languages/frameworks
like Typescript/Node.js , C#/DotNet ,...

In case this seems not clear to you.This guy made a great explanation on
this : https://www.youtube.com/watch?v=M3jNn3HMeWg

**Further Explanation**:

- Non-Blocking I/O: In a reactive system, when a thread sends a request to a database, it doesn’t
  wait for the response. Instead, it is freed up to handle other incoming requests. This allows for
  more efficient use of resources and better handling of concurrent operations.

- Event-Driven Model: The completion of the database operation triggers an event. In this
  event-driven model, any available thread can process the response and complete the client's
  request. This mechanism is often referred to as a publish-subscribe (pub-sub) model, where events
  are published and subscribers react to them.

- Resource Efficiency: By not blocking threads during I/O operations, the system can handle more
  concurrent requests with fewer threads. This leads to better resource utilization and scalability,
  allowing the system to perform efficiently under high loads.

- For better details , check out this
  post : https://medium.com/simform-engineering/deep-dive-into-reactive-programming-with-spring-boot-d62cae63bb03

### Advantages of Reactive Programming in Microservices and High I/O Communication:

- **Scalability**: Reactive programming allows microservices to handle a large number of concurrent
  requests efficiently by using fewer threads. This leads to better resource utilization and
  improved scalability.
- **Non-Blocking I/O**: Reactive systems use non-blocking I/O, which means threads are not tied up
  waiting for I/O operations to complete. This results in higher throughput and lower latency.
- **Resilience**: Reactive systems are designed to handle failures gracefully, making them more
  resilient. They can continue to function even when some components fail, ensuring higher
  availability and reliability.
- **Responsive Applications**: By handling requests asynchronously and efficiently, reactive systems
  can provide faster response times, leading to more responsive applications that can handle high
  loads effectively.
- **Resource Efficiency**: Reactive programming reduces the need for a large number of threads,
  leading to more efficient use of CPU and memory resources.
- **Easier Maintenance and Evolution**: Reactive systems are often built as a collection of small,
  independent services (microservices), making it easier to update, maintain, and evolve the system
  over time.

  By adopting Reactive Programming, these issues can be mitigated, allowing for more efficient,
  scalable, and responsive applications.

### Disadvantages of Reactive Programming with Spring Data and Relational Databases

- **Integration with Blocking APIs**: Many existing libraries and frameworks are blocking and
  synchronous. Integrating these with a
  reactive system can be challenging and might require wrapping blocking calls in non-blocking
  wrappers, adding complexity and reducing performance.

- **Database Support**: Not all relational databases and their drivers fully support reactive
  programming.For Spring Data R2DBC , check the full list
  here :https://docs.spring.io/spring-data/r2dbc/docs/3.1.0/reference/html/#r2dbc.drivers

- **Debugging and Tracing:**: Debugging reactive code can be more challenging because the
  asynchronous flow of data and operations
  can obscure the root cause of issues. Traditional debugging tools may not be as effective.

- **Transaction Management**: - Controlling database transactions in this model can be trickier
  since operations don’t execute sequentially or in a blocking manner (e.g., waiting for results to
  finish one step before proceeding to the next).

- **Performance Overheads**: The reactive approach can introduce performance overheads in some
  scenarios. Frequent context switching and wrapping/unwrapping of data can add overhead, making it
  less efficient for certain use cases compared to traditional blocking operations.## Disadvantages
  of Blocking Communication:

_**Recommend to read these docs before
coding**_: https://docs.spring.io/spring-data/r2dbc/docs/3.1.0/reference/html/

Everything has its own use case. We've been talking about Reactive Programming and Non-Blocking
Communication, let's peek into Blocking Communication and Imperative Programming to have a complete
overview of both.

### Advantage of Blocking Communication

- **Simplicity**: Blocking communication is straightforward and easier to understand & control
  the workflow.

- **Easier Debugging**: Debugging and tracing issues can be more intuitive since the flow of
  execution is sequential and easier to follow.

- **Predictable Performance**: With well-understood performance characteristics, blocking I/O can
  sometimes offer predictable and stable performance for applications with low concurrency.

- **Wide Support**: Many existing systems, protocols, and libraries are designed around blocking
  I/O, making integration straightforward.Additionally, The ecosystem around blocking communication is mature, with extensive
  libraries, tools, and frameworks available.

### Disadvantage of Blocking Communication

- **Resource Intensive**: Blocking communication requires a separate thread for each request,
  leading to high resource consumption as the number of concurrent users increases.
- **Scalability Issues**: As the number of users grows, the server quickly reaches its thread limit,
  causing additional requests to queue up and increasing response times.
- **High Latency**: Each thread waits for I/O operations to complete, leading to increased latency,
  particularly under heavy load.
- **Inefficient Use of Resources**: Threads waiting for I/O operations are not performing any useful
  work, resulting in inefficient use of CPU and memory resources.
- **Complex Error Handling**: Managing timeouts and retries for blocking I/O can complicate error
  handling and recovery processes.
- **Difficulty in Maintaining Throughput**: Maintaining high throughput becomes challenging as the
  server must balance processing time among all active threads, leading to potential performance
  bottlenecks.

## ⛔️🤓 **_Enough of nerdy stuff , this section discusses every API in this project_**