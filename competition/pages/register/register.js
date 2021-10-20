// pages/person/login.js
const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    keyUrl:app.globalData.apiUrl+"/assets/register/key.png",
    nameUrl:app.globalData.apiUrl+"/assets/register/name.png",
    logoUrl:app.globalData.apiUrl+"/assets/register/logo.png"
  },
  login:function(e){
    var userLogin=e.detail.value;
    if(userLogin.username.length==0){
        wx.showToast({
          title: '用戶名不能为空!',
          icon: 'error',
          duration: 2000
        })
        setTimeout(function () {
          wx.hideToast()
        }, 1000)
    }else if(userLogin.password.length==0){
      wx.showToast({
        title: '密碼不能为空!',
        icon: 'error',
        duration: 2000
      })
      setTimeout(function () {
        wx.hideToast()
      }, 1000)
    }else{
      wx.login({
        success(res){
          wx.request({
            url: app.globalData.apiUrl+'/wx/register',
            method: "POST", //提交方式
            header: {
              'content-type': 'application/x-www-form-urlencoded'
            },
            data: {
              username:userLogin.username,
              password:userLogin.password,
              code:res.code,
              rawData:wx.getStorageSync('rawData')
            },success(res){
              if(res.data.status!=200){
                wx.showToast({
                  title:  res.data.msg,
                  icon: 'error',
                  duration: 2000
                })
                setTimeout(function () {
                  wx.hideToast()
                }, 1000)
              }else {
                app.globalData.hasBind=true;
                wx.setStorageSync('hasBind', true);
                wx.setStorageSync('skey', res.data.data.skey);
                wx.setStorageSync('studentId', res.data.data.studentId);
                wx.showToast({
                  title: res.data.msg,
                  icon: 'success',
                  duration: 2000
                })
                setTimeout(function () {
                  wx.hideToast()
                }, 1000)
                wx.switchTab({ //保留当前页面，跳转到应用内的某个页面
                  url: '../index/index', //跳转的页面
                })
              }
            }
          })
        }
      })

    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})