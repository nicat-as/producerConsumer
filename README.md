# Producer Consumer pattern 
This application is implementing producer consumer pattern for multithread application. Producer consumer pattern simply uses queue for loose coupling.  **ExecutorService** is used for creating thread pool. Some important code snippets:
**Producer class** 
```java
@Component
public class Producer implements Runnable {

    private BlockingQueue<Number> queue;

    @Autowired
    private NumbersRepository numbersRepository;

    public Producer(BlockingQueue<Number> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            List<Number> numbers = numbersRepository.getNumberStatus(1);
            if (!numbers.isEmpty() && !queue.contains(numbers)) {
                queue.addAll(numbers);
                System.out.println("Queue : " + queue.size() + " number added to queue : " + numbers);
            } else {
                System.out.println("There are no numbers in queue");
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
```
**Consumer class**

```java
@Component
public class Consumer implements Runnable {

    //this is reference for shared queue
    private BlockingQueue<Number> queue;

    @Autowired
    private NumbersRepository numbersRepository;

    public Consumer(BlockingQueue<Number> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        try {
            Number number = queue.take();
            Optional<Number> numberOptional = Optional.of(number);
            if (numberOptional.isPresent()) {
                System.out.println("Number is taken : " + number);
                numbersRepository.setStatus(number.getId());
            } else {
                System.out.println("There is no number in queue for consuming!!");
            }
        } catch (Exception e) {

        }
    }
}
```
**Service class**
```java
@Service
public class NumberServiceImpl implements NumberService {

    private final Consumer consumer;
    private final Producer producer;

    public NumberServiceImpl(Consumer consumer, Producer producer) {
        this.consumer = consumer;
        this.producer = producer;
    }

    @Override
    public void execute(BlockingQueue<Number> queue) {
        int count = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService producerExec = Executors.newScheduledThreadPool(count);
        ScheduledExecutorService consumerExec = Executors.newScheduledThreadPool(count);

        producerExec.scheduleAtFixedRate(producer, 5, 5, TimeUnit.SECONDS);
        consumerExec.scheduleAtFixedRate(consumer, 5, 5, TimeUnit.SECONDS);


    }
}
```
