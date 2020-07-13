import Vue from 'vue'
import Router from 'vue-router'

import routes from './adminRouters'
import store from "../store";

Vue.use(Router);

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
const router = new Router(
    {
      routes,
      mode: 'history'
    }
)

const initRouters = (store) => {
  // 这个人登录了已经
  if (store.state.user.userId) {
    // 路由加载过了
    if (store.state.app.hasRouters && store.state.app.routers && store.state.app.routers.length > 0) {
      console.log('已经加载过了路由')
    } else {
      // 加载路由
      console.log('开始加载路由权限...')
      store.dispatch('getRouters').then(routers => {
        // 此处routers已经是按照权限过滤后的路由了，没权限的，不加入路由，无法访问
        // 路由重置一下把404放最后
        const newRouter = new Router({
          routes,
          mode: 'history'
        })
        router.matcher = newRouter.matcher
        // 把404加最后面，如果用router.push({name:'xxxx'})这种的话，404页面可能空白，用path:'/aa/bb'
      //   router.addRoutes(routers.concat([{
      //     path: '*',
      //     name: 'error_404',
      //     meta: {
      //       hideInMenu: true
      //     },
      //     component: () => import('@/view/error-page/404.vue')
      //   }]))
      }).finally(() => {
      })
    }
  }
}
initRouters(store)
router.beforeEach((to,from,next)=>{
  // console.log(store.state.app.routers)
  next()
})


export default router
