import { Component, OnInit } from "@angular/core";
import { Store } from "@ngrx/store";
import { selectUser } from "src/app/auth/state/auth.selectors";

@Component({
  selector: "app-settings",
  templateUrl: "./settings.component.html",
  styleUrls: ["./settings.component.scss"],
})
export class SettingsComponent implements OnInit {
  user$ = this.store.select(selectUser());

  constructor(private store: Store) {}

  ngOnInit(): void {}
}
