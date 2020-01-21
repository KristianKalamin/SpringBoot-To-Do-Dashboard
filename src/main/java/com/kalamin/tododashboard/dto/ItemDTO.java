package com.kalamin.tododashboard.dto;

import com.kalamin.tododashboard.annotations.ValidDate;
import org.jetbrains.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ItemDTO {

    private long id;

    @NotEmpty
    @NotNull
    private String title;

    @NotEmpty
    @NotNull
    private String description;

    private int priority;

    private String itemError;

    @ValidDate
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @ValidDate
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    @Nullable
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@Nullable LocalDate startDate) {
        this.startDate = startDate;
    }

    @Nullable
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@Nullable LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemError() {
        return itemError;
    }

    public void setItemError(String itemError) {
        this.itemError = itemError;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
