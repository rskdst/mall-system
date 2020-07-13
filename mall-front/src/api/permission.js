import {get,post} from '../utils/http'


//登录
export const login = info => {
  return post({
    url:"permission/login",
    data:info
  })
};

export const getSiderMenuList = info => { //获取侧边栏菜单
  return post({
    url:"/permission/getMenuListByRole",
    data:info
  })
};

//菜单列表相关接口
export const getMenuList =  () => { //获取数据列表
  return post({
    url:"/permission/getMenuList",
  })
};
export const saveMenu = info => { //添加菜单
  return post({
    url: "/permission/saveMenuInfo",
    data: info
  })
};
export const updateMenu = info => { //添加菜单
  return post({
    url: "/permission/updateMenuInfo",
    data: info
  })
};
export const getMenuListByCondition = info => {
  return post({
    url:"/permission/getMenuListByCondition",
    data:info
  })
};
export const getAllRoleList = () => {
  return post({
    url:"/permission/getAllRole"
  })
};

