<template>
  <div>
    <add-menu ref="addMenu" v-show="addFlag" :data="data" :rowData="currentRowData" :addflag="addFlag"
              @addClose="addclose"></add-menu>
    <search :searchForm="searchForm"></search>
    <menu-header :addFlag="addFlag"></menu-header>
    <data-list :outColumns="columns" :outData="data" :innerColumns="innerColumns" :innerData="innerData"
               :pagination="pagination" :addFlag="addFlag"></data-list>
  </div>
</template>

<script>
  import {getAllRoleList} from "@/api/permission";
  import addMenu from "@/components/admin/permission/role/addMenu";
  import search from "@/components/admin/permission/common/search";
  import menuHeader from "@/components/admin/permission/common/menuHeader";
  import dataList from "@/components/admin/permission/common/dataList";

  export default {
    name: "roleIndex",
    components: {
      addMenu,
      search,
      menuHeader,
      dataList
    },
    data() {
      return {
        addFlag: false,
        currentRowData: [],
        searchForm: [
          {
            title: "菜单名称",
            value: "",
            type: "input"
          }, {
            title: "显示",
            value: "",
            type: "select"
          }
        ],
        pagination: {
          pageSize: 5, // 一页的数据限制
          current: 1, // 当前页
          total: 0, // 总数
          hideOnSinglePage: false, // 只有一页时是否隐藏分页器
          showQuickJumper: true, // 是否可以快速跳转至某页
          showSizeChanger: true, // 是否可以改变 pageSize
          pageSizeOptions: ['5', '10', '20'], // 指定每页可以显示多少条
          showTotal: total => `共 ${total}条`, // 可以展示总数
          onShowSizeChange: (current, pageSize) => { // 改变 pageSize时的回调
            this.pagination.current = current
            this.pagination.pageSize = pageSize
            //   ....
          },
          onChange: (current) => { // 切换分页时的回调，
            // 当在页面定义change事件时，切记要把此处的事件清除，因为这两个事件重叠了，可能到时候会导致一些莫名的bug
            this.pagination.current = current
          },
        },
        columns: [
          {
            title: '编号',
            dataIndex: 'bianhao',
            key: 'bianhao',
            align: "center",
            width: 100,
          }, {
            title: '角色名称',
            dataIndex: 'name',
            align: "center",
            key: 'name',
            width: 200,
          }, {
            title: '描述',
            dataIndex: 'description',
            align: "center",
            key: 'description',
            width: 300,
          }, {
            title: '用户数',
            dataIndex: 'userNum',
            align: "center",
            key: 'userNum',
            width: 100,
          }, {
            title: '添加时间',
            dataIndex: 'createdDate',
            key: 'createdDate',
            align: "center",
            width: 200,
          }, {
            title: '是否显示',
            dataIndex: 'show',
            key: 'show',
            align: "center",
            width: 100,
            scopedSlots: {customRender: 'show'},
          }, {
            title: '操作',
            dataIndex: 'operation',
            key: 'operation',
            align: "center",
            width: 300,
            scopedSlots: {customRender: 'operation'}
          }
        ],
        data: [],
        innerColumns: [
          {
            title: '编号',
            dataIndex: 'innerBianhao',
            key: 'innerBianhao',
            align: "center",
            width: 100,
          }, {
            title: '角色名称',
            dataIndex: 'innerName',
            align: "center",
            key: 'innerName',
            width: 200,
          }, {
            title: '描述',
            dataIndex: 'innerDescription',
            align: "center",
            key: 'innerDescription',
            width: 300,
          }, {
            title: '用户数',
            dataIndex: 'innerUserNum',
            align: "center",
            key: 'innerUserNum',
            width: 100,
          }, {
            title: '添加时间',
            dataIndex: 'innerCreatedDate',
            key: 'innerCreatedDate',
            align: "center",
            width: 250,
          }, {
            title: '是否显示',
            dataIndex: 'innerShow',
            key: 'innerShow',
            align: "center",
            width: 100,
            scopedSlots: {customRender: 'innerShow'},
          }
        ],
        innerData: [],
      }
    },
    methods: {
      search() {
        this.data = [];
        // getMenuListByCondition(this.searchForm).then(res => {
        //   let menuData = res.data;
        //   this.getData(menuData);
        // })
      },
      addclose(addflag) { //关闭添加角色界面
        this.addFlag = addflag
      },
      initSearchForm() {
        this.searchForm = [
          {
            title: "菜单名称",
            value: "",
            type: "input"
          }, {
            title: "显示",
            value: "",
            type: "select"
          }
        ]
      },
      menuAdd() {
        this.addFlag = !this.addFlag;
        this.$refs.addMenu.initForm();
      },
      addclose(addflag) {
        this.addFlag = addflag
      },
      getData(roleData) { //将取到的数据放入data中渲染页面
        this.pagination.total = roleData.length;
        for (let i in roleData) {
          //获取父级菜单名称
          let pid = roleData[i].pid;
          let parentName;
          if (pid == "0") {
            parentName = "无上级菜单";
          } else {
            let menuList = this.$store.state.app.allMenuList;
            for (let j in menuList) {
              if (menuList[j].menuid == pid) {
                parentName = menuList[j].menuName;
              }
            }
          }
          //获取所有子菜单
          let menuid = menuData[i].menuid;
          let sonMenuList = [];
          for (let k in menuData) {
            if (menuData[k].pid == menuid) {
              let innerJsonData = {
                "innerBianhao": menuData[k].menuid,
                "key": k.toString(),
                "innerName": menuData[k].menuName,
                "innerLevel": menuData[k].level,
                "innerAddress": menuData[k].path,
                "innerIcon": menuData[k].icon,
                "innerShow": menuData[k].showmenu == "0" ? false : true,
                "innerSort": menuData[k].sort,
              }
              sonMenuList.push(innerJsonData)
            }
          }
          // console.log(menuData[i].showmenu)
          let jsonData = {
            "id": menuData[i].id,
            "bianhao": menuData[i].menuid,
            "key": i.toString(),
            "name": menuData[i].menuName,
            "level": menuData[i].level,
            "address": menuData[i].path,
            "icon": menuData[i].icon,
            "sort": menuData[i].sort,
            "parent": parentName,
            "show": [menuData[i].showmenu == "0" ? false : true, i],
            "operation": [{title: "编辑", index: i}, {title: "删除", index: i}],
            "expand": sonMenuList,
          };
          this.data.push(jsonData)

        }
      }
    },
    mounted() {
      getAllRoleList().then(res => {
        console.log(res)
      })
    }
  }
</script>

<style scoped>

</style>
