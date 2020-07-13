import {getSiderMenuList} from "../../api/permission"
import {backendMenusToRouters} from "../../utils/utils";

import user from './user'
export default {
  state:{
    routers:[],
    hasRouters:false,
    menuList:[],
    allMenuList:[],
  },
  mutations:{
    setRouters (state,routers){
      state.routers = routers
    },
    setHasRouters (state,hasRouters){
      state.hasRouters = hasRouters
    },
    setMenuList (state,menuList){
      state.menuList = menuList
    },
    setAllMenuList (state,allMenuList){
      state.allMenuList = allMenuList
    },
  },
  actions:{
    getRouters({ commit }){
      return new Promise((resolve, reject) => {
        try {
          getSiderMenuList({id:user.state.userId}).then(res => {
            let routers = backendMenusToRouters(res.data)
            commit('setRouters', routers)
            commit('setHasRouters', true)
            resolve(routers)
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },

    }
}
