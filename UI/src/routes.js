import React from "react";
import DefaultLayout from "./containers/DefaultLayout";

const Category = React.lazy(() => import("./views/Base/Forms/CategoryForm"));
const ItemForm = React.lazy(() => import("./views/Base/Forms/ItemForm"));

const Dashboard = React.lazy(() => import("./views/Dashboard"));

// https://github.com/ReactTraining/react-router/tree/master/packages/react-router-config
const routes = [
  { path: "/", exact: true, name: "Home", component: DefaultLayout },
  { path: "/dashboard", name: "Dashboard", component: Dashboard },

  { path: "/category", name: "Category", component: Category },
  { path: "/item", name: "Item", component: ItemForm }
];

export default routes;
