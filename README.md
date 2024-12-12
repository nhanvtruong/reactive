# Spring Reactive Demo

**Disclaimer**: These documents are compiled from various sources on the internet, carefully curated
to provide the most coherent and sensible information. However, they are not an absolute source of
truth. If you have any concerns, please verify the information carefully.

## Typical Blocking Request Handling

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

## Non-Blocking Request Handling in Spring Reactive

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

In case of this seems not clear.This guy made a great explanation on
this : https://www.youtube.com/watch?v=M3jNn3HMeWg

**Further Explanation**:

- Non-Blocking I/O: In a reactive system, when a thread sends a request to a database, it doesn’t
  wait
  for the response. Instead, it is released to handle other incoming requests.

- Event-Driven Model: The completion of the database operation triggers an event. The event-driven
  model ensures that once the database responds, any free thread can process the response and
  complete
  the client's request.

- Resource Efficiency: By not blocking threads during I/O operations, the system can handle more
  concurrent requests with fewer threads, leading to better resource utilization and scalability.

## Disadvantages of Blocking Communication:

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

## Advantages of Reactive Programming in Microservices and High I/O Communication:

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
