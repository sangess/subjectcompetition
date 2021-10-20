// pages/person/person.js
var util = require('../../utils/util.js')
var app=getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
    hasUserInfo: false,
    hasBind:false,
    nickname: '',
    footImageUrl:app.globalData.apiUrl+"/assets/person/foot.png",
    messageImage:app.globalData.apiUrl+"/assets/person/message.png",
    praseImageUrl:app.globalData.apiUrl+"/assets/person/prase.png",
  },
  /**
   * 生命周期函数--监听页面加载
   */

  onLoad: function (options) {
    let that =this;
    if (wx.getStorageSync("userInfo")) {
      that.setData({
        userInfo: wx.getStorageSync("userInfo"),
        hasUserInfo:true
      }) 
   }
   if (wx.getStorageSync("hasBind")) {

    that.setData({
      hasBind: wx.getStorageSync("hasBind"),
    }) 
 }

  },
  /**登录 */
  login:function(){
    let that=this;
    wx.getUserProfile({
          desc: "获取你的昵称、头像、地区及性别",
          success: data => {
            that.setData({
              userInfo: data.userInfo,
              hasUserInfo: true
            }),  
            wx.setStorageSync('userInfo',data.userInfo);
            wx.setStorageSync('rawData', data.rawData);
            wx.login({
              success: function(login_res) {
                wx.request({
                  url: app.globalData.apiUrl+'/wx/onLogin',
                  method: 'POST',
                  header: {
                    'content-type': 'application/x-www-form-urlencoded'
                  },
        
                  data: {
                    code: login_res.code, //临时登录凭证
                    rawData: wx.getStorageSync('rawData'), //用户非敏感信息
                    encrypteData:data.encrypteData, //用户敏感信息
                    iv: data.iv //解密算法的向量
                  },
                  success: function(res) {
                    if (res.data.status == 200) {
                      that.setData({
                        hasBind:true
                      }),
                      // 7.小程序存储skey（自定义登录状态）到本地
                      wx.setStorageSync('hasBind', true);
                      wx.setStorageSync('skey', res.data.data.skey);
                      wx.setStorageSync('studentId', res.data.data.studentId);
                    } 
                  },
                  fail: function(error) {
                    //调用服务端登录接口失败
                    console.log(error);
                  }
                })
              }
            })
          }
    });
  },
  toRegister: function(e) {
    wx.navigateTo({ //保留当前页面，跳转到应用内的某个页面
      url: '../register/register', //跳转的页面
    })
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
    let that=this;
    if (wx.getStorageSync("hasBind")) {
     that.setData({
       hasBind: wx.getStorageSync("hasBind"),
     }) 
  }
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