<template>
  <div class="admin">
    <el-container>
      <el-header>
        <!-- 页面标题 -->
        <div class="page-title">后台管理系统</div>
        <!-- 用户信息 -->
        <div class="user-info">
          <span class="user-name">{{ userInfo.name }}</span>
          <el-dropdown trigger="click" class="user-dropdown">
            <span class="el-dropdown-link">
              <i class="el-icon-arrow-down"></i>
            </span>
            <el-dropdown-menu>
              <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <!-- 左侧菜单 -->
        <el-aside width="200px">
          <el-menu :default-active="$route.path" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
            <el-submenu v-for="item in menu" :key="item.id" :index="item.id">
              <template #title>
                <i :class="item.icon"></i>
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item v-for="subItem in item.children" :key="subItem.id" :index="subItem.path">
                {{ subItem.name }}
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <!-- 右侧内容 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Admin",
  data() {
    return {
      userInfo: {
        name: "admin",
      },
      menu: [
        {
          id: "1",
          name: "首页",
          icon: "el-icon-s-home",
          children: [
            {
              id: "1-1",
              name: "欢迎",
              path: "/admin/home",
            },
          ],
        },
        {
          id: "2",
          name: "文章管理",
          icon: "el-icon-tickets",
          children: [
            {
              id: "2-1",
              name: "文章列表",
              path: "/admin/articles",
            },
            {
              id: "2-2",
              name: "发布文章",
              path: "/admin/article/create",
            },
          ],
        },
        {
          id: "3",
          name: "用户管理",
          icon: "el-icon-user",
          children: [
            {
              id: "3-1",
              name: "用户列表",
              path: "/admin/users",
            },
          ],
        },
      ],
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleLogout() {
      // 退出登录操作
    },
  },
};
</script>

<style scoped>
.admin {
  height: 100%;
  background-color: #f5f7fa;
  font-size: 14px;
}

.page-title {
  font-size: 20px;
  font-weight: bold;
}

.user-info {
  margin-left: auto;
}

.user-name {
  margin-right: 10px;
}

.el-aside {
  background-color: #fff;
}
</style>