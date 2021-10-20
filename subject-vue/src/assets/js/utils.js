
/**
 * 日期时间的格式化 
 * 用法：
 * let date = new Date();
 * dateFormat("YYYY-mm-dd HH:MM", date);
 * @param {*} fmt 指定日期格式化
 * @param {*} date 日期
 */
function dateFormat(fmt, date) {
    let ret;
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}

function formatDate (date, fmt) {
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    let o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    }
    for (let k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            let str = o[k] + ''
            fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : padLeftZero(str))
        }
    }
    return fmt
}

function padLeftZero (str) {
    return ('00' + str).substr(str.length)
}


import FileSaver from "file-saver";
import XLSX from "xlsx";
/**
 * 导出Excel表格
 * @param {*} tableDOM  table节点（ID）
 * @param {*} excelName  导出的excel名称
 */
function exportExcel(tableDOM, excelName) {
    /* generate workbook object from table */
    var wb = XLSX.utils.table_to_book(document.querySelector("#" + tableDOM));
    /* get binary string as output */
    var wbout = XLSX.write(wb, {
        bookType: "xlsx",
        bookSST: true,
        type: "array"
    });
    try {
        FileSaver.saveAs(
            new Blob([wbout], { type: "application/octet-stream" }),
            excelName + ".xlsx"
        );
    } catch (e) {
        if (typeof console !== "undefined") console.log(e, wbout);
    }
    return wbout;
}



/**
 * 这个sleep()在执行的时候是“block”程序的继续执行的。它不是同步的。如果想让它同步执行，不妨碍执行之后的代码，我们可以使用 async/await 关键字。
 * 使用方法：
 * (async function() {
 * console.log('Do some thing, ' + new Date());
 * await sleep(3000);
 * console.log('Do other things, ' + new Date());
 * })();
 * @param {*} time 毫秒
 */
function sleep(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

export { //很关键
    dateFormat,
    exportExcel,
    sleep,
    formatDate,
    padLeftZero
}
