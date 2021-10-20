// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

   /* // 登录
    wx.login({
      success: res => {
        console.log(res.code);
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })*/
  },
  globalData: {
    userInfo: null,
    token:"",
    apiUrl:"http://localhost:8080"
    /*http://8.142.21.132:8080 */
    /*http://localhost:8080 */
  }
})
