const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}

const getNowTime = () =>{
  let now = new Date();
  return now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
}

const getStartTime = () =>{
  let now = new Date();
  let nowYear = now.getFullYear();
  return  (nowYear-2)+"-01-01";
}

module.exports = {
  formatTime,
  getNowTime,
  getStartTime
}
