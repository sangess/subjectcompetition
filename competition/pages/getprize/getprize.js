// pages/getprize/getprize.js
var util = require('../../utils/util.js')
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    awards:[],
    imgList:[],
  },
   imgYu: function (event) {
     var src = event.currentTarget.dataset.src;//获取data-src
     var imgList = event.currentTarget.dataset.list;//获取data-list
     //图片预览
     wx.previewImage({
       current: src, // 当前显示图片的http链接
       urls: imgList // 需要预览的图片http链接列表
     })
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
    wx.request({
      url: app.globalData.apiUrl+'/wx/getStudentAwards',
      method:'GET',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data:{
        studentId:wx.getStorageSync('studentId'),
      },
      success:(res)=>{
        this.setData({
          awards:res.data,
        });
        var imgList_old=this.data.imgList;
        var awards=this.data.awards;
        for(var i=0;i<awards.length;i++){
          this.setData({
            imgList:imgList_old.concat(awards[i].imageUrl),
          })
        }
      }
    })
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