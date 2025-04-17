import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-calendar",
  templateUrl: "./calendar.component.html",
  styleUrls: ["./calendar.component.scss"],
})
export class CalendarComponent implements OnInit {
  selectedDate: Date | null = null;

  constructor() {}

  ngOnInit(): void {}

  onDateSelected(date: Date | null) {
    console.log("Date clicked:", date);
  }

  dateClass = (d: Date) => {
    const today = new Date();
    const isToday =
      d.getDate() === today.getDate() &&
      d.getMonth() === today.getMonth() &&
      d.getFullYear() === today.getFullYear();

    return isToday ? "highlight-today" : "";
  };
}
