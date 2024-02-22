package com.bcsd.domain.dataset.event;

/**
 * @Author liukun.inspire
 * @Date 2024/2/21 19:21
 * @PackageName: com.bcsd.domain.dataset.event
 * @ClassName: DomainEvent
 * @Version 1.0
 */
public abstract class DomainEvent<T> {

    private String id;

    private long timestamp;

    private String source;

    private T data;

    public DomainEvent(T data) {
        this.data = data;
    }

    public DomainEvent(String source, T data) {
        this.source = source;
        this.data = data;
    }

    public String getId() {

        return id;
    }

    /**
     * @param id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    /**
     * @param source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    public T getData() {
        return data;
    }

    /**
     * @param data to set
     */
    public void setData(T data) {
        this.data = data;
    }
}
