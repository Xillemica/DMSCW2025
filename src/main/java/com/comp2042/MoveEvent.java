package com.comp2042;

/**
 * Represents a movement event in the game.
 * <p>
 * Encapsulates the type of action performed and its source.
 * This is typically used to inform the game controller of user or automated input.
 */
public final class MoveEvent {

    /** The type of the move event (e.g., LEFT, RIGHT, DOWN, ROTATE). */
    private final EventType eventType;

    /** The source of the move event (e.g., USER, THREAD). */
    private final EventSource eventSource;

    /**
     * Constructs a new {@code MoveEvent} with the specified event type and source.
     *
     * @param eventType the type of the event
     * @param eventSource the source of the event
     */
    public MoveEvent(EventType eventType, EventSource eventSource) {
        this.eventType = eventType;
        this.eventSource = eventSource;
    }

    /**
     * Returns the type of this move event.
     *
     * @return the event type
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Returns the source of this move event.
     *
     * @return the event source
     */
    public EventSource getEventSource() {
        return eventSource;
    }
}
