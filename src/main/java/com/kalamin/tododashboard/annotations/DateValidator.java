package com.kalamin.tododashboard.annotations;

import org.jetbrains.annotations.NotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDate> {
   public void initialize(ValidDate constraint) {
   }

   public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
      if (date == null)
         return true;
      return checkDate(date);
   }

   private boolean checkDate(@NotNull LocalDate date) {
      LocalDate localDate = LocalDate.now();
      return date.compareTo(localDate) >= 0;
   }

}
