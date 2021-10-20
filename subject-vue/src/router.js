import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'

Vue.use(Router)

var router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },

    {
      path: '/admin',
      meta: {
        requireAuth: true, // 添加该字段，表示进入这个路由是需要登录的
        power: "admin" //权限
      },
      component: () => import('./views/admin/admin.vue'),
      children: [
          {
          path: '/admin/index',
          name: 'admin_index',
          meta: {
            requireAuth: true,
            power: "admin",
          },
          component: () => import('./views/admin/admin_index.vue'),
        },

        {
          path: '/admin/comp',
          name: 'admin_comp',
          meta: {
            requireAuth: true,
            power: "admin"
          },
          component: () => import('./views/admin/admin_comp.vue'),
        },

        {
          path: '/admin/xinxi',
          name: 'admin_xinxi',
          meta: {
            requireAuth: true,
            power: "admin"
          },
          component: () => import('./views/admin/admin_xinxi.vue'),
        },

        {
          path: '/admin/comp/add',
          name: 'admin_comp_add',
          meta: {
            requireAuth: true,
            power: "admin"
          },
          component: () => import('./views/admin/admin_comp_add.vue'),
        },

        {
          path: '/admin/project',
          name: 'admin_project',
          meta: {
            requireAuth: true,
            power: "admin"
          },
          component: () => import('./views/admin/admin_project.vue'),
        },
        {
          path: '/admin/gonggao',
          name: 'admin_gonggao',
          meta: {
            requireAuth: true,
            power: "admin"
          },
          component: () => import('./views/admin/admin_gonggao.vue'),
        },
        {
          path: '/admin/tongji',
          name: 'admin_tongji',
          meta: {
            requireAuth: true,
            power: "admin"
          },
          component: () => import('./views/admin/admin_tongji.vue'),
        },
        {
          path: '',
          redirect: '/admin/index'

        },
        {
          path: '/admin/home',
          name: 'admin_home',
          meta: {
            requireAuth: true,
            power: "admin"
          },
          component: () => import('./views/admin/admin_home.vue'),
        },
        {
          path: '/admin/apply',
          name: 'admin_apply',
          meta: {
            requireAuth: true,
            power: "admin"
          },
          component: () => import('./views/admin/admin_apply.vue'),
        }
      ]
    },
    //学院
    {
      path: '/college',
      meta: {
        requireAuth: true,
        power: "college"
      },
      component: () => import('./views/college/college.vue'),
      children: [{
          path: '/college/index',
          name: 'college_index',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_index.vue'),
        },
        {
          path: '/college/comp/add',
          name: 'college_comp_add',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_comp_add.vue'),
        },
        {
          path: '/college/project',
          name: 'college_project',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_project.vue'),
        },
        {
          path: '/college/comp',
          name: 'college_comp',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_comp.vue'),
        },
        {
          path: '/college/apply',
          name: 'college_apply',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_apply.vue'),
        },
        {
          path: '/college/work',
          name: 'college_work',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_work.vue'),
        },
        {
          path: '/college/home',
          name: 'college_home',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_home.vue'),
        },
        {
          path: '/college/tongji',
          name: 'college_tongji',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_tongji.vue'),
        },
        {
          path: '/college/xinxi',
          name: 'college_xinxi',
          meta: {
            requireAuth: true,
            power: "college"
          },
          component: () => import('./views/college/college_xinxi.vue'),
        },

        {
          path: '',
          redirect: '/college/index'

        },
      ]
    },
    //学生
    {
      path: '/student',
      meta: {
        requireAuth: true,
        power: "student"
      },
      component: () => import('./views/student/student.vue'),
      children: [{
        path: '/student/index',
        name: 'student_index',
        meta: {
          requireAuth: true,
          power: "student"
        },
        component: () => import('./views/student/student_index.vue'),
      },
        {
          path: '/student/project',
          name: 'student_project',
          meta: {
            requireAuth: true,
            power: "student"
          },
          component: () => import('./views/student/student_project.vue'),
        },
        {
          path: '/student/comp',
          name: 'student_comp',
          meta: {
            requireAuth: true,
            power: "student"
          },
          component: () => import('./views/student/student_comp.vue'),
        },
        {
          path: '/student/apply',
          name: 'student_apply',
          meta: {
            requireAuth: true,
            power: "student"
          },
          component: () => import('./views/student/student_apply.vue'),
        },
        {
          path: '/student/home',
          name: 'student_home',
          meta: {
            requireAuth: true,
            power: "student"
          },
          component: () => import('./views/student/student_home.vue'),
        },
        {
          path: '/student/xinxi',
          name: 'student_xinxi',
          meta: {
            requireAuth: true,
            power: "student"
          },
          component: () => import('./views/student/student_xinxi.vue'),
        },
        {
          path: '/student/work',
          name: 'student_work',
          meta: {
            requireAuth: true,
            power: "student"
          },
          component: () => import('./views/student/student_work.vue'),
        },
        {
          path: '',
          redirect: '/student/index'

        },
      ]
    },
    //教师
    {
      path: '/teacher',
      meta: {
        requireAuth: true,
        power: "teacher"
      },
      component: () => import('./views/teacher/teacher.vue'),
      children: [{
        path: '/teacher/index',
        name: 'teacher_index',
        meta: {
          requireAuth: true,
          power: "teacher"
        },
        component: () => import('./views/teacher/teacher_index.vue'),
      },
        {
          path: '/teacher/project',
          name: 'teacher_project',
          meta: {
            requireAuth: true,
            power: "teacher"
          },
          component: () => import('./views/teacher/teacher_project.vue'),
        },
        {
          path: '/teacher/comp',
          name: 'teacher_comp',
          meta: {
            requireAuth: true,
            power: "teacher"
          },
          component: () => import('./views/teacher/teacher_comp.vue'),
        },
        {
          path: '/teacher/apply',
          name: 'teacher_apply',
          meta: {
            requireAuth: true,
            power: "teacher"
          },
          component: () => import('./views/teacher/teacher_apply.vue'),
        },
        {
          path: '/teacher/home',
          name: 'teacher_home',
          meta: {
            requireAuth: true,
            power: "teacher"
          },
          component: () => import('./views/teacher/teacher_home.vue'),
        },
        {
          path: '/teacher/xinxi',
          name: 'teacher_xinxi',
          meta: {
            requireAuth: true,
            power: "teacher"
          },
          component: () => import('./views/teacher/teacher_xinxi.vue'),
        },

        {
          path: '',
          redirect: '/teacher/index'

        },
      ]
    },

    {
      path: '*',
      redirect: '/'
    }

  ]
})



export default router;

//路由守卫
router.beforeEach((to, from, next) => {
  var islogin = window.sessionStorage.getItem("islogin");
  var token = window.sessionStorage.getItem("token");
  var path = "/" + window.sessionStorage.getItem("token");
  // console.log("是否登录：" + islogin);
  // console.log("权限：" + token);
  if (to.meta.requireAuth) {
    // console.log("前往的页面: " + to.path + " 需要登陆");
    if (islogin == 'true') {
      // console.log("已经登录···");
      if (to.meta.power == token) {
        // console.log("拥有相应的访问权限: " + to.meta.power);
        next();
      } else {
        // console.log("没有相应的访问权限: " + to.meta.power);
        // console.log("前往拥有的权限页面: " + path);
        next(path);
      }
    } else {
      // console.log("没有登录，前往登陆页面···");
      next("/");
    }
  } else if (to.path == "/") {
    // console.log("前往的页面: " + to.path + " 不需要登陆");
    // console.log("前往的页面: " + to.path + " 是登录页面");
    // console.log();
    if (islogin == 'true') {
      // console.log("islogin: " + islogin + "已经登录···");
      // console.log("前往拥有的权限页面: " + path);
      next(path);
    } else {
      // console.log("islogin: " + islogin + "没有登录，登陆页面放行···");
      next();
    }
  } else {
    // console.log("前往的页面: " + to.path + " 不需要登陆");
    // console.log("前往的页面: " + to.path + " 不是登录页面，放行");
    next();
  }

})