import java.time.LocalDate;

interface RequestProduct {
    void setPriority();
    void setStatus();
    void setExpire();
    void processRequest();
}

class LowPriorityConcrete implements RequestProduct {
    private String priority;
    private String status;
    private LocalDate expireDay;
    
    @Override
    public void setPriority() {
        this.priority = "Ignore";
    }

    @Override
    public void setStatus() {
        this.status = "Done";
    }

    @Override
    public void setExpire() {
        this.expireDay = LocalDate.now();
    }

    @Override
    public void processRequest() {
        System.out.println("Request denied");
    }
}

class MidPriorityConcrete implements RequestProduct {
    private String priority;
    private String status;
    private LocalDate expireDay;
    
    @Override
    public void setPriority() {
        this.priority = "Medium";
    }

    @Override
    public void setStatus() {
        this.status = "Accepted";
    }

    @Override
    public void setExpire() {
        this.expireDay = LocalDate.now().plusMonths(1);
    }

    @Override
    public void processRequest() {
        System.out.println("Request accept, estimated completion date is " + expireDay);
    }
}

class HighPriorityConcrete implements RequestProduct {
    private String priority;
    private String status;
    private LocalDate expireDay;
    
    @Override
    public void setPriority() {
        this.priority = "Emergency";
    }

    @Override
    public void setStatus() {
        this.status = "Accept";
    }

    @Override
    public void setExpire() {
        this.expireDay = LocalDate.now();
    }

    @Override
    public void processRequest() {
        System.out.println("Emergency request, our Administrator will contact you immediately!");
    }
}

abstract class RequestCreator {
    public abstract RequestProduct createRequest();
    
    public void processRequest() {
        RequestProduct request = createRequest();
        request.setPriority();
        request.setStatus();
        request.setExpire();
        request.processRequest();
    }
}

class LowPriorityConcreteCreator extends RequestCreator {
    @Override
    public RequestProduct createRequest() {
        return new LowPriorityConcrete();
    }
}

class MidPriorityConcreteCreator extends RequestCreator {
    @Override
    public RequestProduct createRequest() {
        return new MidPriorityConcrete();
    }
}

class HighPriorityConcreteCreator extends RequestCreator {
    @Override
    public RequestProduct createRequest() {
        return new HighPriorityConcrete();
    }
}

public class MaintenanceRequestDemo {
    public static void main(String[] args) {
        RequestCreator lowPriority = new LowPriorityConcreteCreator();
        RequestCreator midPriority = new MidPriorityConcreteCreator();
        RequestCreator highPriority = new HighPriorityConcreteCreator();
        
        System.out.println("Processing Low-Priority Request:");
        lowPriority.processRequest();
        
        System.out.println("\nProcessing Medium-Priority Request:");
        midPriority.processRequest();
        
        System.out.println("\nProcessing High-Priority Request:");
        highPriority.processRequest();
    }
}
