import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryComponent} from "./components/category.component";
import { AuthGuard } from 'src/app/core/guards/auth.guard';

const routes: Routes = [
    { path: '', component: CategoryComponent, canActivate: [AuthGuard] },
    { path: 'create', component: CategoryComponent, canActivate: [AuthGuard] },
    { path: 'edit/:id', component: CategoryComponent, canActivate: [AuthGuard] },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class CategoryRoutingModule {}
