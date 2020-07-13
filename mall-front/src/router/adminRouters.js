import login from '@/view/admin/login'
import admin from '@/view/admin/index'

export default [
  {
    path: '/',
    name: 'login',
    component: login,
  },
  {
    path: '/admin',
    name: 'admin',
    component: admin,
    children:[
      {
        path:"permission/menu",
        name:"menu",
        component: () => import("@/view/admin/permission/menu/menuIndex.vue")
      },
      {
        path: "permission/role",
        name: "role",
        component: () => import("@/view/admin/permission/role/roleIndex.vue")
      },
    ]
  }
]



