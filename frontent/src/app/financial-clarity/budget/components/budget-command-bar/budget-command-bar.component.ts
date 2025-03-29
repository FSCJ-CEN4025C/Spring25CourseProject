import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { Router } from "@angular/router";
import { CommandBarActions } from "../../enums/command-bar-actions.enum";


@Component({
  selector: "app-budget-command-bar",
  templateUrl: "./budget-command-bar.component.html",
  styleUrls: ["./budget-command-bar.component.scss"],
})
export class BudgetCommandBarComponent implements OnInit {
  @Output() action = new EventEmitter<CommandBarActions>();
  constructor(private router: Router) {}

  ngOnInit(): void {}

  emitAction(action: CommandBarActions) {
    this.action.emit(action);
  }

  logOut() {
    localStorage.removeItem("token");
    this.router.navigateByUrl("/login").then();
  }
}
