// pages/myself/myself.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasBind:"",
    studentInfo:{},
    userInfo:{},
    hasUserInfo:false,
    competitions: [],
      compInfo:{ id: 1,  img:"/images/3.png",title:"参加竞赛总数"},
      scoreInfo:{ id: 2,img:"/images/1.png",  title: "当前分数" },
      listInfo:{ id: 3,img:"/images/2.png", title: "校内名次" },
      compCount:"",
      score:"",
      list:""

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
  isset:function (key) {
    let val = wx.getStorageSync(key)
    return val !== '' && val !== null & val !== undefined
   },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that=this;
    var ifLogin=this.isset('userInfo');
    var ifBind=this.isset('hasBind');
    var ifhasStudentId=this.isset('studentId');
    if(ifLogin==false){
      wx.showToast({
        title: '尚未授权登录!',
        icon: 'error',
        duration: 2000
      })
      setTimeout(function () {
        wx.hideToast();
        wx.switchTab({
          url: '../person/person',
        })
      }, 1000)
    }else if(ifBind==false&&ifhasStudentId==false){
        wx.showToast({
          title: '还未绑定学号哦!',
          icon: 'error',
          duration: 2000
        })
        setTimeout(function () {
          wx.hideToast();
          wx.navigateTo({
            url: '../register/register',
          })
        }, 1000)
      }else{
        that.setData({
          hasUserInfo:true,
          userInfo:wx.getStorageSync('userInfo'),
          hasBind:wx.getStorageSync('hasBind'),
        });

        wx.request({
          url: app.globalData.apiUrl+'/wx/mySelf',
          method: "GET", //提交方式
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data:{
            skey:wx.getStorageSync('skey'),
            studentId:wx.getStorageSync('studentId'),
          },
          success:function(res){
            that.setData({
              studentInfo:res.data,
            });
          }
        });
        wx.request({
          url: app.globalData.apiUrl+'/wx/studentProjects',
          method: "GET", //提交方式
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data:{
            studentId:wx.getStorageSync('studentId'),
          },
          success:function(res){
            that.setData({
              competitions:res.data
            });
            console.log(res.data);
          }
        });
        wx.request({
          url: app.globalData.apiUrl+'/wx/getStudentAwardsInfo',
          method: "GET", //提交方式
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data:{
            studentId:wx.getStorageSync('studentId'),
          },
          success:function(res){
            that.setData({
              compCount:res.data.compCount,
              score:res.data.score,
              list:res.data.list,
            });
            console.log(res.data);
          }
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