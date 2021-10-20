// pages/comps/comps.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    item: 0,
    tab: 0,
    hasBind:wx.getStorageSync('hasBind'),
    myComps: [],
    collegeComps:[],
    applyList:[],
    checkTeamImageUrl:app.globalData.apiUrl+"/assets/comp/check.png",
    exitTeamImageUrl:app.globalData.apiUrl+"/assets/comp/exit.jpg",
    sigupImageUrl:app.globalData.apiUrl+"/assets/comp/signup.png",
    invokeImageUrl:app.globalData.apiUrl+"/assets/comp/invoke.png",
    isRuleTrue:false,
    projectInfo:{}
  },
  hideRule: function () {
    this.setData({
        isRuleTrue: false
    })
  },
  showRule: function (e) {
    let that=this;
    let projectId=e.currentTarget.dataset.projectid;
    wx.request({
      url: app.globalData.apiUrl+'/wx/getProjectInfo',
      method:'GET',
      data:{
        projectId:projectId
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success:function(res){
        that.setData({
          projectInfo:res.data,
        })
      }
    });
    this.setData({
        isRuleTrue: true
    })
  },



    // 页面切换
    changeItem: function(e) {
      let that=this;
      that.setData({
        item: e.target.dataset.item,
      });
      that.onShow();
    },
    // tab切换
    changeTab: function(e) {
      let that=this;
      that.setData({
        tab: e.detail.current
      });
      that.onShow();
    },
    joinProject:function(e){
      let that=this;
      let projectId=e.currentTarget.dataset.projectid;
      wx.request({
        url: app.globalData.apiUrl+'/wx/joinProject',
        method:'GET',
        data:{
          studentId:wx.getStorageSync('studentId'),
          projectId:projectId
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success:function(res){
          console.log(res.data);
          if(res.data.code!=200){
            wx.showToast({
              title: res.data.message,
              icon: 'error',
              duration: 2000
            })
            setTimeout(function () {
              wx.hideToast();
            }, 2000)
          }else{
            wx.showToast({
              title: res.data.message,
              icon: 'success',
              duration: 2000
            })
            setTimeout(function () {
              wx.hideToast();
              that.onShow();
            }, 2000)
          }
        }
      })
    },

    showExit(e){
      var projectId=e.currentTarget.dataset.projectid;
        this.popup.showPopup(projectId);
    },
    _error(){
      this.popup.hidePopup();
    },
    _success() {
      var projectId=this.popup.data.dataSelf;
      let that=this;
      this.exitProject(projectId);
      setTimeout(function () {
        that.popup.hidePopup();
      }, 1000);
     that.onShow();
      
    },
    exitProject(projectId){
      let that=this;
      wx.request({
        url: app.globalData.apiUrl+'/wx/exitProject',
        method: "GET", //提交方式
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        data:{
          projectId:projectId,
          studentId:wx.getStorageSync('studentId'),
        },
        success:function(res){
          wx.showToast({
            title: res.data.message,
            icon: res.data.code==200?'success':'error',
            duration: 2000
          })
          setTimeout(function () {
            wx.hideToast();
            that.onShow();
          }, 1000);
        }
      });
    },

    showInvoke(e){
      var projectId=e.currentTarget.dataset.projectid;
        this.invoke.showPopup(projectId);
    },
    invoke_error(){
      this.invoke.hidePopup();
    },
    invoke_success() {
      var projectId=this.invoke.data.dataSelf;
      let that=this;
      this.invokeApply(projectId);
      setTimeout(function () {
        that.invoke.hidePopup();
        that.onShow();
      }, 1000);

    },
    invokeApply(projectId){
  
      wx.request({
        url: app.globalData.apiUrl+'/wx/invokeApply',
        method: "GET", //提交方式
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        data:{
          projectId:projectId,
          studentId:wx.getStorageSync('studentId'),
        },
        success:function(res){
          wx.showToast({
            title: res.data.message,
            icon: res.data.code==200?'success':'error',
            duration: 2000
          })
          setTimeout(function () {
            wx.hideToast();
          }, 1000)
        }
      });
    },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  isset:function (key) {
    let val = wx.getStorageSync(key)
    return val !== '' && val !== null & val !== undefined
   },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.popup = this.selectComponent("#popup");
    this.invoke = this.selectComponent("#invoke");
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
          hasBind:wx.getStorageSync('hasBind'),
        });
        wx.request({
          url: app.globalData.apiUrl+'/wx/myComps',
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
              myComps:res.data
            });
          }
        });
        wx.request({
          url: app.globalData.apiUrl+'/wx/collegeComps',
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
              collegeComps:res.data
            });
          }
        });
        wx.request({
          url: app.globalData.apiUrl+'/wx/myApplyList',
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
              applyList:res.data
            });
          }
        });
        
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