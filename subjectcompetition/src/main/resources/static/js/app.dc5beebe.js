(function(e){function n(n){for(var r,a,i=n[0],u=n[1],d=n[2],s=0,l=[];s<i.length;s++)a=i[s],Object.prototype.hasOwnProperty.call(o,a)&&o[a]&&l.push(o[a][0]),o[a]=0;for(r in u)Object.prototype.hasOwnProperty.call(u,r)&&(e[r]=u[r]);h&&h(n);while(l.length)l.shift()();return c.push.apply(c,d||[]),t()}function t(){for(var e,n=0;n<c.length;n++){for(var t=c[n],r=!0,a=1;a<t.length;a++){var i=t[a];0!==o[i]&&(r=!1)}r&&(c.splice(n--,1),e=u(u.s=t[0]))}return e}var r={},a={app:0},o={app:0},c=[];function i(e){return u.p+"js/"+({}[e]||e)+"."+{"chunk-085e41f8":"77617ae1","chunk-0891e9fc":"2972fe0d","chunk-0f951055":"3a1fd237","chunk-047aa85c":"9bd454e8","chunk-2d0be6fb":"fee97a82","chunk-2d0d7d5c":"bb478974","chunk-2d0e4568":"ab6a12a6","chunk-2d0f1135":"5a9938e2","chunk-2d2bd504":"dfafb69c","chunk-51725a37":"45d6e19b","chunk-54fdfc88":"d140d9fd","chunk-5bb490aa":"240bb146","chunk-986dc690":"20243dde","chunk-11bd50b2":"34ad7785","chunk-1644fafb":"156370c4","chunk-18ea8058":"0846cf6d","chunk-25e6e0b3":"ccd8b1a6","chunk-2d0c8a27":"8022b16e","chunk-2d0cc051":"93175481","chunk-2d0d604f":"ff03576b","chunk-2d0d9ffc":"f1a7e23f","chunk-2d0da982":"2f55734d","chunk-2d0e2304":"a375ede1","chunk-2d229223":"6f0c6436","chunk-2d230883":"9fe4a37e","chunk-3998bf54":"b6070953","chunk-3d89b3f8":"295804ef","chunk-617acdf8":"6565b6a7","chunk-739240b2":"6df52883","chunk-806cd5ba":"6b3d7e32","chunk-9c1c3d46":"68cfe52d","chunk-b59d0b66":"e1d43a27"}[e]+".js"}function u(n){if(r[n])return r[n].exports;var t=r[n]={i:n,l:!1,exports:{}};return e[n].call(t.exports,t,t.exports,u),t.l=!0,t.exports}u.e=function(e){var n=[],t={"chunk-085e41f8":1,"chunk-0891e9fc":1,"chunk-047aa85c":1,"chunk-2d2bd504":1,"chunk-51725a37":1,"chunk-54fdfc88":1,"chunk-5bb490aa":1,"chunk-986dc690":1,"chunk-11bd50b2":1,"chunk-1644fafb":1,"chunk-18ea8058":1,"chunk-25e6e0b3":1,"chunk-3998bf54":1,"chunk-3d89b3f8":1,"chunk-617acdf8":1,"chunk-806cd5ba":1,"chunk-9c1c3d46":1,"chunk-b59d0b66":1};a[e]?n.push(a[e]):0!==a[e]&&t[e]&&n.push(a[e]=new Promise((function(n,t){for(var r="css/"+({}[e]||e)+"."+{"chunk-085e41f8":"fbf91ba8","chunk-0891e9fc":"fbf91ba8","chunk-0f951055":"31d6cfe0","chunk-047aa85c":"29895d29","chunk-2d0be6fb":"31d6cfe0","chunk-2d0d7d5c":"31d6cfe0","chunk-2d0e4568":"31d6cfe0","chunk-2d0f1135":"31d6cfe0","chunk-2d2bd504":"bfe75b5b","chunk-51725a37":"29895d29","chunk-54fdfc88":"29895d29","chunk-5bb490aa":"86d761f8","chunk-986dc690":"035ad306","chunk-11bd50b2":"1728b300","chunk-1644fafb":"d3c6b465","chunk-18ea8058":"d3c6b465","chunk-25e6e0b3":"e468e816","chunk-2d0c8a27":"31d6cfe0","chunk-2d0cc051":"31d6cfe0","chunk-2d0d604f":"31d6cfe0","chunk-2d0d9ffc":"31d6cfe0","chunk-2d0da982":"31d6cfe0","chunk-2d0e2304":"31d6cfe0","chunk-2d229223":"31d6cfe0","chunk-2d230883":"31d6cfe0","chunk-3998bf54":"fa7f9eaa","chunk-3d89b3f8":"1728b300","chunk-617acdf8":"1728b300","chunk-739240b2":"31d6cfe0","chunk-806cd5ba":"fbf91ba8","chunk-9c1c3d46":"9ee8acf5","chunk-b59d0b66":"fbf91ba8"}[e]+".css",o=u.p+r,c=document.getElementsByTagName("link"),i=0;i<c.length;i++){var d=c[i],s=d.getAttribute("data-href")||d.getAttribute("href");if("stylesheet"===d.rel&&(s===r||s===o))return n()}var l=document.getElementsByTagName("style");for(i=0;i<l.length;i++){d=l[i],s=d.getAttribute("data-href");if(s===r||s===o)return n()}var h=document.createElement("link");h.rel="stylesheet",h.type="text/css",h.onload=n,h.onerror=function(n){var r=n&&n.target&&n.target.src||o,c=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");c.code="CSS_CHUNK_LOAD_FAILED",c.request=r,delete a[e],h.parentNode.removeChild(h),t(c)},h.href=o;var m=document.getElementsByTagName("head")[0];m.appendChild(h)})).then((function(){a[e]=0})));var r=o[e];if(0!==r)if(r)n.push(r[2]);else{var c=new Promise((function(n,t){r=o[e]=[n,t]}));n.push(r[2]=c);var d,s=document.createElement("script");s.charset="utf-8",s.timeout=120,u.nc&&s.setAttribute("nonce",u.nc),s.src=i(e);var l=new Error;d=function(n){s.onerror=s.onload=null,clearTimeout(h);var t=o[e];if(0!==t){if(t){var r=n&&("load"===n.type?"missing":n.type),a=n&&n.target&&n.target.src;l.message="Loading chunk "+e+" failed.\n("+r+": "+a+")",l.name="ChunkLoadError",l.type=r,l.request=a,t[1](l)}o[e]=void 0}};var h=setTimeout((function(){d({type:"timeout",target:s})}),12e4);s.onerror=s.onload=d,document.head.appendChild(s)}return Promise.all(n)},u.m=e,u.c=r,u.d=function(e,n,t){u.o(e,n)||Object.defineProperty(e,n,{enumerable:!0,get:t})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,n){if(1&n&&(e=u(e)),8&n)return e;if(4&n&&"object"===typeof e&&e&&e.__esModule)return e;var t=Object.create(null);if(u.r(t),Object.defineProperty(t,"default",{enumerable:!0,value:e}),2&n&&"string"!=typeof e)for(var r in e)u.d(t,r,function(n){return e[n]}.bind(null,r));return t},u.n=function(e){var n=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(n,"a",n),n},u.o=function(e,n){return Object.prototype.hasOwnProperty.call(e,n)},u.p="/",u.oe=function(e){throw console.error(e),e};var d=window["webpackJsonp"]=window["webpackJsonp"]||[],s=d.push.bind(d);d.push=n,d=d.slice();for(var l=0;l<d.length;l++)n(d[l]);var h=s;c.push([0,"chunk-vendors"]),t()})({0:function(e,n,t){e.exports=t("56d7")},"56d7":function(e,n,t){"use strict";t.r(n);t("cadf"),t("551c"),t("f751"),t("097d");var r=t("2b0e"),a=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{attrs:{id:"app"}},[e.isRouterAlive?t("router-view"):e._e()],1)},o=[],c={name:"app",provide:function(){return{reload:this.reload}},data:function(){return{isRouterAlive:!0}},mounted:function(){var e=window.sessionStorage.getItem("token");this.$store.commit("setToken",e);var n=window.sessionStorage.getItem("islogin");this.$store.commit("loginTure",n);var t=window.sessionStorage.getItem("username");this.$store.commit("setUsername",t);var r=window.sessionStorage.getItem("id");this.$store.commit("setId",r);var a=window.sessionStorage.getItem("receiver");this.$store.commit("setReceiver",a);var o=window.sessionStorage.getItem("nickname");this.$store.commit("setNickname",o)},methods:{reload:function(){this.isRouterAlive=!1,this.$nextTick((function(){this.isRouterAlive=!0}))}}},i=c,u=t("2877"),d=Object(u["a"])(i,a,o,!1,null,null,null),s=d.exports,l=t("8c4f"),h=function(){var e=this,n=e.$createElement,r=e._self._c||n;return r("div",{staticClass:"home"},[r("div",{class:[{aactive:e.iisActive},"errorClass"]}),r("div",{staticStyle:{position:"relative"},on:{mouseover:e.addActive,mouseleave:e.removeActive}},[r("div",{on:{mouseover:e.adActive,mouseleave:e.emoveActive}},[r("el-card",{staticStyle:{padding:"0"},attrs:{id:"form"}},[r("div",{class:[{aa:e.aisActive},"errorClass"]},[r("div",{staticStyle:{padding:"-1px"},attrs:{slot:"header"},slot:"header"},[r("img",{attrs:{src:t("8f1f"),alt:""}})]),r("div",{staticStyle:{padding:"0px 0px 0px 0px"}},[r("el-form",{ref:"form",attrs:{model:e.form,"label-width":"79px",rules:e.rules}},[r("el-form-item",{staticStyle:{margin:"5px 70px 25px 15px"},attrs:{label:"用户名",prop:"username"}},[r("el-input",{model:{value:e.form.username,callback:function(n){e.$set(e.form,"username",n)},expression:"form.username"}})],1),r("el-form-item",{staticStyle:{margin:"5px 70px 25px 15px"},attrs:{label:"密码",prop:"password"}},[r("el-input",{attrs:{type:"password"},model:{value:e.form.password,callback:function(n){e.$set(e.form,"password",n)},expression:"form.password"}})],1),r("el-form-item",{staticStyle:{margin:"5px 70px 25px 15px"},attrs:{label:"登录身份",prop:"token"}},[r("el-radio-group",{model:{value:e.form.token,callback:function(n){e.$set(e.form,"token",n)},expression:"form.token"}},[r("el-radio",{attrs:{label:"student",value:"student"}},[e._v("学生")]),r("el-radio",{attrs:{label:"teacher",value:"teacher"}},[e._v("教师")]),r("el-radio",{attrs:{label:"college",value:"college"}},[e._v("学院管理员")]),r("el-radio",{attrs:{label:"admin",value:"admin"}},[e._v("校级管理员")])],1)],1),r("el-button",{staticStyle:{width:"249px","margin-top":"10px"},attrs:{type:"primary",round:""},on:{click:function(n){return e.submitForm("form")}}},[e._v("登 录")])],1)],1)])])],1)]),e._m(0)])},m=[function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{staticClass:"dong"},[t("ul",[t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li"),t("li")])])}],f={name:"home",data:function(){return{iisActive:!1,aisActive:!1,form:{username:"",password:"",token:""},rules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"change"}],token:[{required:!0,message:"请选择登陆身份",trigger:"change"}]}}},mounted:function(){},methods:{addActive:function(){this.iisActive=!0},removeActive:function(){this.iisActive=!1},adActive:function(){this.aisActive=!0},emoveActive:function(){this.aisActive=!1},loginUser:function(){var e=this;this.$axios.post(I+"/login",e.form).then((function(n){switch(n.data.code){case 2001:e.$message({type:"error",message:n.data.message});break;case 2003:switch(e.$store.commit("loginTure",!0),e.$store.commit("setUsername",n.data.user.username),e.$store.commit("setNickname",n.data.user.nickname),e.$store.commit("setReceiver",n.data.user.targetId),e.$store.commit("setToken",n.data.user.token),e.$store.commit("setId",n.data.user.id),window.sessionStorage.setItem("username",n.data.user.username),window.sessionStorage.setItem("token",n.data.user.token),window.sessionStorage.setItem("nickname",n.data.user.nickname),window.sessionStorage.setItem("id",n.data.user.id),window.sessionStorage.setItem("receiver",n.data.user.targetId),window.sessionStorage.setItem("islogin",!0),e.$message({message:n.data.message,type:"success"}),n.data.user.token){case"admin":e.$router.push({path:"/admin"});break;case"college":e.$router.push({path:"/college"});break;case"student":e.$router.push({path:"/student"});break;case"teacher":e.$router.push({path:"/teacher"});break}break;default:e.$message({message:n.data.message,type:"error"});break}})).catch((function(e){console.log("error"),console.log(e)}))},submitForm:function(e){var n=this;this.$refs[e].validate((function(e){if(!e)return!1;n.loginUser()}))}}},p=f,b=(t("8a9f"),t("97b0"),Object(u["a"])(p,h,m,!1,null,"49857f8b",null)),k=b.exports;r["default"].use(l["a"]);var g=new l["a"]({mode:"history",base:"/",routes:[{path:"/",name:"login",component:k},{path:"/admin",meta:{requireAuth:!0,power:"admin"},component:function(){return t.e("chunk-085e41f8").then(t.bind(null,"8b28"))},children:[{path:"/admin/index",name:"admin_index",meta:{requireAuth:!0,power:"admin"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-5bb490aa")]).then(t.bind(null,"482d"))}},{path:"/admin/comp",name:"admin_comp",meta:{requireAuth:!0,power:"admin"},component:function(){return t.e("chunk-617acdf8").then(t.bind(null,"a5aa"))}},{path:"/admin/xinxi",name:"admin_xinxi",meta:{requireAuth:!0,power:"admin"},component:function(){return t.e("chunk-2d0d604f").then(t.bind(null,"7187"))}},{path:"/admin/comp/add",name:"admin_comp_add",meta:{requireAuth:!0,power:"admin"},component:function(){return t.e("chunk-2d0d9ffc").then(t.bind(null,"6a72"))}},{path:"/admin/project",name:"admin_project",meta:{requireAuth:!0,power:"admin"},component:function(){return t.e("chunk-9c1c3d46").then(t.bind(null,"12c6"))}},{path:"/admin/gonggao",name:"admin_gonggao",meta:{requireAuth:!0,power:"admin"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-2d2bd504")]).then(t.bind(null,"dff9"))}},{path:"/admin/tongji",name:"admin_tongji",meta:{requireAuth:!0,power:"admin"},component:function(){return t.e("chunk-739240b2").then(t.bind(null,"0999"))}},{path:"",redirect:"/admin/index"},{path:"/admin/home",name:"admin_home",meta:{requireAuth:!0,power:"admin"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-986dc690")]).then(t.bind(null,"83ab"))}},{path:"/admin/apply",name:"admin_apply",meta:{requireAuth:!0,power:"admin"},component:function(){return t.e("chunk-2d0da982").then(t.bind(null,"6bee"))}}]},{path:"/college",meta:{requireAuth:!0,power:"college"},component:function(){return t.e("chunk-b59d0b66").then(t.bind(null,"ba63"))},children:[{path:"/college/index",name:"college_index",meta:{requireAuth:!0,power:"college"},component:function(){return t.e("chunk-3998bf54").then(t.bind(null,"c2bf"))}},{path:"/college/project",name:"college_project",meta:{requireAuth:!0,power:"college"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-2d0e4568")]).then(t.bind(null,"9094"))}},{path:"/college/comp",name:"college_comp",meta:{requireAuth:!0,power:"college"},component:function(){return t.e("chunk-3d89b3f8").then(t.bind(null,"3ccf"))}},{path:"/college/apply",name:"college_apply",meta:{requireAuth:!0,power:"college"},component:function(){return t.e("chunk-2d0cc051").then(t.bind(null,"4bc0"))}},{path:"/college/home",name:"college_home",meta:{requireAuth:!0,power:"college"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-047aa85c")]).then(t.bind(null,"67b4"))}},{path:"/college/xinxi",name:"college_xinxi",meta:{requireAuth:!0,power:"college"},component:function(){return t.e("chunk-2d0c8a27").then(t.bind(null,"565e"))}},{path:"",redirect:"/college/index"}]},{path:"/student",meta:{requireAuth:!0,power:"student"},component:function(){return t.e("chunk-806cd5ba").then(t.bind(null,"5b94"))},children:[{path:"/student/index",name:"student_index",meta:{requireAuth:!0,power:"student"},component:function(){return t.e("chunk-1644fafb").then(t.bind(null,"09c1"))}},{path:"/student/project",name:"student_project",meta:{requireAuth:!0,power:"student"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-2d0d7d5c")]).then(t.bind(null,"7904"))}},{path:"/student/comp",name:"student_comp",meta:{requireAuth:!0,power:"student"},component:function(){return t.e("chunk-25e6e0b3").then(t.bind(null,"c1d8"))}},{path:"/student/apply",name:"student_apply",meta:{requireAuth:!0,power:"student"},component:function(){return t.e("chunk-2d0e2304").then(t.bind(null,"7e44"))}},{path:"/student/home",name:"student_home",meta:{requireAuth:!0,power:"student"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-54fdfc88")]).then(t.bind(null,"02c1"))}},{path:"/student/xinxi",name:"student_xinxi",meta:{requireAuth:!0,power:"student"},component:function(){return t.e("chunk-2d230883").then(t.bind(null,"ed4b"))}},{path:"",redirect:"/student/index"}]},{path:"/teacher",meta:{requireAuth:!0,power:"teacher"},component:function(){return t.e("chunk-0891e9fc").then(t.bind(null,"8893"))},children:[{path:"/teacher/index",name:"teacher_index",meta:{requireAuth:!0,power:"teacher"},component:function(){return t.e("chunk-18ea8058").then(t.bind(null,"6ca1"))}},{path:"/teacher/project",name:"teacher_project",meta:{requireAuth:!0,power:"teacher"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-2d0f1135")]).then(t.bind(null,"9ea4"))}},{path:"/teacher/comp",name:"teacher_comp",meta:{requireAuth:!0,power:"teacher"},component:function(){return t.e("chunk-11bd50b2").then(t.bind(null,"b03d"))}},{path:"/teacher/apply",name:"teacher_apply",meta:{requireAuth:!0,power:"teacher"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-2d0be6fb")]).then(t.bind(null,"2ff7"))}},{path:"/teacher/home",name:"teacher_home",meta:{requireAuth:!0,power:"teacher"},component:function(){return Promise.all([t.e("chunk-0f951055"),t.e("chunk-51725a37")]).then(t.bind(null,"0b0b"))}},{path:"/teacher/xinxi",name:"teacher_xinxi",meta:{requireAuth:!0,power:"teacher"},component:function(){return t.e("chunk-2d229223").then(t.bind(null,"dbc2"))}},{path:"",redirect:"/teacher/index"}]},{path:"*",redirect:"/"}]}),v=g;g.beforeEach((function(e,n,t){var r=window.sessionStorage.getItem("islogin"),a=window.sessionStorage.getItem("token"),o="/"+window.sessionStorage.getItem("token");e.meta.requireAuth?"true"==r?e.meta.power==a?t():t(o):t("/"):"/"==e.path&&"true"==r?t(o):t()}));var w=t("2f62");t("3fd3");r["default"].use(w["a"]);var x=new w["a"].Store({state:{islogin:!1,id:"",username:"",nickname:"",receiver:"",token:""},mutations:{loginTure:function(e,n){e.islogin=n},setId:function(e,n){e.id=n},setUsername:function(e,n){e.username=n},setNickname:function(e,n){e.nickname=n},setToken:function(e,n){e.token=n},setReceiver:function(e,n){e.receiver=n},logout:function(e){e.userinfo={},e.islogin=!1}},actions:{}}),A=t("bc3a"),y=t.n(A),_=t("5c96"),q=t.n(_),S=(t("0fae"),t("313e")),$=t.n(S);r["default"].prototype.$axios=y.a,r["default"].config.productionTip=!1,r["default"].use(q.a),r["default"].prototype.$echarts=$.a,new r["default"]({router:v,store:x,render:function(e){return e(s)}}).$mount("#app");var j="http://8.142.21.132:22:80",I=n["default"]=j},"8a9f":function(e,n,t){"use strict";t("9fe3")},"8f1f":function(e,n,t){e.exports=t.p+"img/logo1.a5ca6889.png"},"97b0":function(e,n,t){"use strict";t("a816")},"9fe3":function(e,n,t){},a816:function(e,n,t){}});
//# sourceMappingURL=app.dc5beebe.js.map