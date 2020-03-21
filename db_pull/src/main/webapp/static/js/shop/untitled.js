!function (e, t) {
    function n(e) {
        var t = e.length,
            n = ut.type(e);
        return ut.isWindow(e) ? !1 : 1 === e.nodeType && t ? !0 : "array" === n || "function" !== n && (0 === t || "number" == typeof t && t > 0 && t - 1 in e)
    }

    function r(e) {
        var t = Nt[e] = {};
        return ut.each(e.match(ct) || [], function (e, n) {
            t[n] = !0
        }), t
    }

    function i(e, n, r, i) {
        if (ut.acceptData(e)) {
            var o, a, s = ut.expando,
                u = "string" == typeof n,
                l = e.nodeType,
                c = l ? ut.cache : e,
                f = l ? e[s] : e[s] && s;
            if (f && c[f] && (i || c[f].data) || !u || r !== t) return f || (l ? e[s] = f = Z.pop() || ut.guid++ : f = s), c[f] || (c[f] = {}, l || (c[f].toJSON = ut.noop)), ("object" == typeof n || "function" == typeof n) && (i ? c[f] = ut.extend(c[f], n) : c[f].data = ut.extend(c[f].data, n)), o = c[f], i || (o.data || (o.data = {}), o = o.data), r !== t && (o[ut.camelCase(n)] = r), u ? (a = o[n], null == a && (a = o[ut.camelCase(n)])) : a = o, a
        }
    }

    function o(e, t, n) {
        if (ut.acceptData(e)) {
            var r, i, o, a = e.nodeType,
                u = a ? ut.cache : e,
                l = a ? e[ut.expando] : ut.expando;
            if (u[l]) {
                if (t && (o = n ? u[l] : u[l].data)) {
                    ut.isArray(t) ? t = t.concat(ut.map(t, ut.camelCase)) : t in o ? t = [t] : (t = ut.camelCase(t), t = t in o ? [t] : t.split(" "));
                    for (r = 0, i = t.length; i > r; r++) delete o[t[r]];
                    if (!(n ? s : ut.isEmptyObject)(o)) return
                }
                (n || (delete u[l].data, s(u[l]))) && (a ? ut.cleanData([e], !0) : ut.support.deleteExpando || u != u.window ? delete u[l] : u[l] = null)
            }
        }
    }

    function a(e, n, r) {
        if (r === t && 1 === e.nodeType) {
            var i = "data-" + n.replace(kt, "-$1").toLowerCase();
            if (r = e.getAttribute(i), "string" == typeof r) {
                try {
                    r = "true" === r ? !0 : "false" === r ? !1 : "null" === r ? null : +r + "" === r ? +r : Ct.test(r) ? ut.parseJSON(r) : r
                } catch (o) {
                }
                ut.data(e, n, r)
            } else r = t
        }
        return r
    }

    function s(e) {
        var t;
        for (t in e) if (("data" !== t || !ut.isEmptyObject(e[t])) && "toJSON" !== t) return !1;
        return !0
    }

    function u() {
        return !0
    }

    function l() {
        return !1
    }

    function c(e, t) {
        do e = e[t];
        while (e && 1 !== e.nodeType);
        return e
    }

    function f(e, t, n) {
        if (t = t || 0, ut.isFunction(t)) return ut.grep(e, function (e, r) {
            var i = !!t.call(e, r, e);
            return i === n
        });
        if (t.nodeType) return ut.grep(e, function (e) {
            return e === t === n
        });
        if ("string" == typeof t) {
            var r = ut.grep(e, function (e) {
                return 1 === e.nodeType
            });
            if (It.test(t)) return ut.filter(t, r, !n);
            t = ut.filter(t, r)
        }
        return ut.grep(e, function (e) {
            return ut.inArray(e, t) >= 0 === n
        })
    }

    function p(e) {
        var t = Ut.split("|"),
            n = e.createDocumentFragment();
        if (n.createElement) for (; t.length;) n.createElement(t.pop());
        return n
    }

    function d(e, t) {
        return e.getElementsByTagName(t)[0] || e.appendChild(e.ownerDocument.createElement(t))
    }

    function h(e) {
        var t = e.getAttributeNode("type");
        return e.type = (t && t.specified) + "/" + e.type, e
    }

    function g(e) {
        var t = on.exec(e.type);
        return t ? e.type = t[1] : e.removeAttribute("type"), e
    }

    function m(e, t) {
        for (var n, r = 0; null != (n = e[r]); r++) ut._data(n, "globalEval", !t || ut._data(t[r], "globalEval"))
    }

    function y(e, t) {
        if (1 === t.nodeType && ut.hasData(e)) {
            var n, r, i, o = ut._data(e),
                a = ut._data(t, o),
                s = o.events;
            if (s) {
                delete a.handle, a.events = {};
                for (n in s) for (r = 0, i = s[n].length; i > r; r++) ut.event.add(t, n, s[n][r])
            }
            a.data && (a.data = ut.extend({}, a.data))
        }
    }

    function v(e, t) {
        var n, r, i;
        if (1 === t.nodeType) {
            if (n = t.nodeName.toLowerCase(), !ut.support.noCloneEvent && t[ut.expando]) {
                i = ut._data(t);
                for (r in i.events) ut.removeEvent(t, r, i.handle);
                t.removeAttribute(ut.expando)
            }
            "script" === n && t.text !== e.text ? (h(t).text = e.text, g(t)) : "object" === n ? (t.parentNode && (t.outerHTML = e.outerHTML), ut.support.html5Clone && e.innerHTML && !ut.trim(t.innerHTML) && (t.innerHTML = e.innerHTML)) : "input" === n && tn.test(e.type) ? (t.defaultChecked = t.checked = e.checked, t.value !== e.value && (t.value = e.value)) : "option" === n ? t.defaultSelected = t.selected = e.defaultSelected : ("input" === n || "textarea" === n) && (t.defaultValue = e.defaultValue)
        }
    }

    function b(e, n) {
        var r, i, o = 0,
            a = typeof e.getElementsByTagName !== V ? e.getElementsByTagName(n || "*") : typeof e.querySelectorAll !== V ? e.querySelectorAll(n || "*") : t;
        if (!a) for (a = [], r = e.childNodes || e; null != (i = r[o]); o++)!n || ut.nodeName(i, n) ? a.push(i) : ut.merge(a, b(i, n));
        return n === t || n && ut.nodeName(e, n) ? ut.merge([e], a) : a
    }

    function x(e) {
        tn.test(e.type) && (e.defaultChecked = e.checked)
    }

    function T(e, t) {
        if (t in e) return t;
        for (var n = t.charAt(0).toUpperCase() + t.slice(1), r = t, i = kn.length; i--;) if (t = kn[i] + n, t in e) return t;
        return r
    }

    function w(e, t) {
        return e = t || e, "none" === ut.css(e, "display") || !ut.contains(e.ownerDocument, e)
    }

    function N(e, t) {
        for (var n, r, i, o = [], a = 0, s = e.length; s > a; a++) r = e[a], r.style && (o[a] = ut._data(r, "olddisplay"), n = r.style.display, t ? (o[a] || "none" !== n || (r.style.display = ""), "" === r.style.display && w(r) && (o[a] = ut._data(r, "olddisplay", S(r.nodeName)))) : o[a] || (i = w(r), (n && "none" !== n || !i) && ut._data(r, "olddisplay", i ? n : ut.css(r, "display"))));
        for (a = 0; s > a; a++) r = e[a], r.style && (t && "none" !== r.style.display && "" !== r.style.display || (r.style.display = t ? o[a] || "" : "none"));
        return e
    }

    function C(e, t, n) {
        var r = vn.exec(t);
        return r ? Math.max(0, r[1] - (n || 0)) + (r[2] || "px") : t
    }

    function k(e, t, n, r, i) {
        for (var o = n === (r ? "border" : "content") ? 4 : "width" === t ? 1 : 0, a = 0; 4 > o; o += 2)"margin" === n && (a += ut.css(e, n + Cn[o], !0, i)), r ? ("content" === n && (a -= ut.css(e, "padding" + Cn[o], !0, i)), "margin" !== n && (a -= ut.css(e, "border" + Cn[o] + "Width", !0, i))) : (a += ut.css(e, "padding" + Cn[o], !0, i), "padding" !== n && (a += ut.css(e, "border" + Cn[o] + "Width", !0, i)));
        return a
    }

    function E(e, t, n) {
        var r = !0,
            i = "width" === t ? e.offsetWidth : e.offsetHeight,
            o = fn(e),
            a = ut.support.boxSizing && "border-box" === ut.css(e, "boxSizing", !1, o);
        if (0 >= i || null == i) {
            if (i = pn(e, t, o), (0 > i || null == i) && (i = e.style[t]), bn.test(i)) return i;
            r = a && (ut.support.boxSizingReliable || i === e.style[t]), i = parseFloat(i) || 0
        }
        return i + k(e, t, n || (a ? "border" : "content"), r, o) + "px"
    }

    function S(e) {
        var t = Y,
            n = Tn[e];
        return n || (n = A(e, t), "none" !== n && n || (cn = (cn || ut("<iframe frameborder='0' width='0' height='0'/>").css("cssText", "display:block !important")).appendTo(t.documentElement), t = (cn[0].contentWindow || cn[0].contentDocument).document, t.write("<!doctype html><html><body>"), t.close(), n = A(e, t), cn.detach()), Tn[e] = n), n
    }

    function A(e, t) {
        var n = ut(t.createElement(e)).appendTo(t.body),
            r = ut.css(n[0], "display");
        return n.remove(), r
    }

    function j(e, t, n, r) {
        var i;
        if (ut.isArray(t)) ut.each(t, function (t, i) {
            n || Sn.test(e) ? r(e, i) : j(e + "[" + ("object" == typeof i ? t : "") + "]", i, n, r)
        });
        else if (n || "object" !== ut.type(t)) r(e, t);
        else for (i in t) j(e + "[" + i + "]", t[i], n, r)
    }

    function D(e) {
        return function (t, n) {
            "string" != typeof t && (n = t, t = "*");
            var r, i = 0,
                o = t.toLowerCase().match(ct) || [];
            if (ut.isFunction(n)) for (; r = o[i++];)"+" === r[0] ? (r = r.slice(1) || "*", (e[r] = e[r] || []).unshift(n)) : (e[r] = e[r] || []).push(n)
        }
    }

    function L(e, n, r, i) {
        function o(u) {
            var l;
            return a[u] = !0, ut.each(e[u] || [], function (e, u) {
                var c = u(n, r, i);
                return "string" != typeof c || s || a[c] ? s ? !(l = c) : t : (n.dataTypes.unshift(c), o(c), !1)
            }), l
        }

        var a = {},
            s = e === zn;
        return o(n.dataTypes[0]) || !a["*"] && o("*")
    }

    function H(e, n) {
        var r, i, o = ut.ajaxSettings.flatOptions || {};
        for (i in n) n[i] !== t && ((o[i] ? e : r || (r = {}))[i] = n[i]);
        return r && ut.extend(!0, e, r), e
    }

    function q(e, n, r) {
        var i, o, a, s, u = e.contents,
            l = e.dataTypes,
            c = e.responseFields;
        for (s in c) s in r && (n[c[s]] = r[s]);
        for (;
            "*" === l[0];) l.shift(), o === t && (o = e.mimeType || n.getResponseHeader("Content-Type"));
        if (o) for (s in u) if (u[s] && u[s].test(o)) {
            l.unshift(s);
            break
        }
        if (l[0] in r) a = l[0];
        else {
            for (s in r) {
                if (!l[0] || e.converters[s + " " + l[0]]) {
                    a = s;
                    break
                }
                i || (i = s)
            }
            a = a || i
        }
        return a ? (a !== l[0] && l.unshift(a), r[a]) : t
    }

    function M(e, t) {
        var n, r, i, o, a = {},
            s = 0,
            u = e.dataTypes.slice(),
            l = u[0];
        if (e.dataFilter && (t = e.dataFilter(t, e.dataType)), u[1]) for (i in e.converters) a[i.toLowerCase()] = e.converters[i];
        for (; r = u[++s];) if ("*" !== r) {
            if ("*" !== l && l !== r) {
                if (i = a[l + " " + r] || a["* " + r], !i) for (n in a) if (o = n.split(" "), o[1] === r && (i = a[l + " " + o[0]] || a["* " + o[0]])) {
                    i === !0 ? i = a[n] : a[n] !== !0 && (r = o[0], u.splice(s--, 0, r));
                    break
                }
                if (i !== !0) if (i && e["throws"]) t = i(t);
                else try {
                        t = i(t)
                    } catch (c) {
                        return {
                            state: "parsererror",
                            error: i ? c : "No conversion from " + l + " to " + r
                        }
                    }
            }
            l = r
        }
        return {
            state: "success",
            data: t
        }
    }

    function _() {
        try {
            return new e.XMLHttpRequest
        } catch (t) {
        }
    }

    function F() {
        try {
            return new e.ActiveXObject("Microsoft.XMLHTTP")
        } catch (t) {
        }
    }

    function O() {
        return setTimeout(function () {
            Zn = t
        }), Zn = ut.now()
    }

    function B(e, t) {
        ut.each(t, function (t, n) {
            for (var r = (or[t] || []).concat(or["*"]), i = 0, o = r.length; o > i; i++) if (r[i].call(e, t, n)) return
        })
    }

    function P(e, t, n) {
        var r, i, o = 0,
            a = ir.length,
            s = ut.Deferred().always(function () {
                delete u.elem
            }),
            u = function () {
                if (i) return !1;
                for (var t = Zn || O(), n = Math.max(0, l.startTime + l.duration - t), r = n / l.duration || 0, o = 1 - r, a = 0, u = l.tweens.length; u > a; a++) l.tweens[a].run(o);
                return s.notifyWith(e, [l, o, n]), 1 > o && u ? n : (s.resolveWith(e, [l]), !1)
            },
            l = s.promise({
                elem: e,
                props: ut.extend({}, t),
                opts: ut.extend(!0, {
                    specialEasing: {}
                }, n),
                originalProperties: t,
                originalOptions: n,
                startTime: Zn || O(),
                duration: n.duration,
                tweens: [],
                createTween: function (t, n) {
                    var r = ut.Tween(e, l.opts, t, n, l.opts.specialEasing[t] || l.opts.easing);
                    return l.tweens.push(r), r
                },
                stop: function (t) {
                    var n = 0,
                        r = t ? l.tweens.length : 0;
                    if (i) return this;
                    for (i = !0; r > n; n++) l.tweens[n].run(1);
                    return t ? s.resolveWith(e, [l, t]) : s.rejectWith(e, [l, t]), this
                }
            }),
            c = l.props;
        for (R(c, l.opts.specialEasing); a > o; o++) if (r = ir[o].call(l, e, c, l.opts)) return r;
        return B(l, c), ut.isFunction(l.opts.start) && l.opts.start.call(e, l), ut.fx.timer(ut.extend(u, {
            elem: e,
            anim: l,
            queue: l.opts.queue
        })), l.progress(l.opts.progress).done(l.opts.done, l.opts.complete).fail(l.opts.fail).always(l.opts.always)
    }

    function R(e, t) {
        var n, r, i, o, a;
        for (i in e) if (r = ut.camelCase(i), o = t[r], n = e[i], ut.isArray(n) && (o = n[1], n = e[i] = n[0]), i !== r && (e[r] = n, delete e[i]), a = ut.cssHooks[r], a && "expand" in a) {
            n = a.expand(n), delete e[r];
            for (i in n) i in e || (e[i] = n[i], t[i] = o)
        } else t[r] = o
    }

    function W(e, t, n) {
        var r, i, o, a, s, u, l, c, f, p = this,
            d = e.style,
            h = {},
            g = [],
            m = e.nodeType && w(e);
        n.queue || (c = ut._queueHooks(e, "fx"), null == c.unqueued && (c.unqueued = 0, f = c.empty.fire, c.empty.fire = function () {
            c.unqueued || f()
        }), c.unqueued++, p.always(function () {
            p.always(function () {
                c.unqueued--, ut.queue(e, "fx").length || c.empty.fire()
            })
        })), 1 === e.nodeType && ("height" in t || "width" in t) && (n.overflow = [d.overflow, d.overflowX, d.overflowY], "inline" === ut.css(e, "display") && "none" === ut.css(e, "float") && (ut.support.inlineBlockNeedsLayout && "inline" !== S(e.nodeName) ? d.zoom = 1 : d.display = "inline-block")), n.overflow && (d.overflow = "hidden", ut.support.shrinkWrapBlocks || p.always(function () {
            d.overflow = n.overflow[0], d.overflowX = n.overflow[1], d.overflowY = n.overflow[2]
        }));
        for (i in t) if (a = t[i], tr.exec(a)) {
            if (delete t[i], u = u || "toggle" === a, a === (m ? "hide" : "show")) continue;
            g.push(i)
        }
        if (o = g.length) {
            s = ut._data(e, "fxshow") || ut._data(e, "fxshow", {}), "hidden" in s && (m = s.hidden), u && (s.hidden = !m), m ? ut(e).show() : p.done(function () {
                ut(e).hide()
            }), p.done(function () {
                var t;
                ut._removeData(e, "fxshow");
                for (t in h) ut.style(e, t, h[t])
            });
            for (i = 0; o > i; i++) r = g[i], l = p.createTween(r, m ? s[r] : 0), h[r] = s[r] || ut.style(e, r), r in s || (s[r] = l.start, m && (l.end = l.start, l.start = "width" === r || "height" === r ? 1 : 0))
        }
    }

    function $(e, t, n, r, i) {
        return new $.prototype.init(e, t, n, r, i)
    }

    function I(e, t) {
        var n, r = {
                height: e
            },
            i = 0;
        for (t = t ? 1 : 0; 4 > i; i += 2 - t) n = Cn[i], r["margin" + n] = r["padding" + n] = e;
        return t && (r.opacity = r.width = e), r
    }

    function z(e) {
        return ut.isWindow(e) ? e : 9 === e.nodeType ? e.defaultView || e.parentWindow : !1
    }

    var X, U, V = typeof t,
        Y = e.document,
        J = e.location,
        G = e.jQuery,
        Q = e.$,
        K = {},
        Z = [],
        et = "1.9.1",
        tt = Z.concat,
        nt = Z.push,
        rt = Z.slice,
        it = Z.indexOf,
        ot = K.toString,
        at = K.hasOwnProperty,
        st = et.trim,
        ut = function (e, t) {
            return new ut.fn.init(e, t, U)
        },
        lt = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source,
        ct = /\S+/g,
        ft = /^[\s﻿ ]+|[\s﻿ ]+$/g,
        pt = /^(?:(<[\w\W]+>)[^>]*|#([\w-]*))$/,
        dt = /^<(\w+)\s*\/?>(?:<\/\1>|)$/,
        ht = /^[\],:{}\s]*$/,
        gt = /(?:^|:|,)(?:\s*\[)+/g,
        mt = /\\(?:["\\\/bfnrt]|u[\da-fA-F]{4})/g,
        yt = /"[^"\\\r\n]*"|true|false|null|-?(?:\d+\.|)\d+(?:[eE][+-]?\d+|)/g,
        vt = /^-ms-/,
        bt = /-([\da-z])/gi,
        xt = function (e, t) {
            return t.toUpperCase()
        },
        Tt = function (e) {
            (Y.addEventListener || "load" === e.type || "complete" === Y.readyState) && (wt(), ut.ready())
        },
        wt = function () {
            Y.addEventListener ? (Y.removeEventListener("DOMContentLoaded", Tt, !1), e.removeEventListener("load", Tt, !1)) : (Y.detachEvent("onreadystatechange", Tt), e.detachEvent("onload", Tt))
        };
    ut.fn = ut.prototype = {
        jquery: et,
        constructor: ut,
        init: function (e, n, r) {
            var i, o;
            if (!e) return this;
            if ("string" == typeof e) {
                if (i = "<" === e.charAt(0) && ">" === e.charAt(e.length - 1) && e.length >= 3 ? [null, e, null] : pt.exec(e), !i || !i[1] && n) return !n || n.jquery ? (n || r).find(e) : this.constructor(n).find(e);
                if (i[1]) {
                    if (n = n instanceof ut ? n[0] : n, ut.merge(this, ut.parseHTML(i[1], n && n.nodeType ? n.ownerDocument || n : Y, !0)), dt.test(i[1]) && ut.isPlainObject(n)) for (i in n) ut.isFunction(this[i]) ? this[i](n[i]) : this.attr(i, n[i]);
                    return this
                }
                if (o = Y.getElementById(i[2]), o && o.parentNode) {
                    if (o.id !== i[2]) return r.find(e);
                    this.length = 1, this[0] = o
                }
                return this.context = Y, this.selector = e, this
            }
            return e.nodeType ? (this.context = this[0] = e, this.length = 1, this) : ut.isFunction(e) ? r.ready(e) : (e.selector !== t && (this.selector = e.selector, this.context = e.context), ut.makeArray(e, this))
        },
        selector: "",
        length: 0,
        size: function () {
            return this.length
        },
        toArray: function () {
            return rt.call(this)
        },
        get: function (e) {
            return null == e ? this.toArray() : 0 > e ? this[this.length + e] : this[e]
        },
        pushStack: function (e) {
            var t = ut.merge(this.constructor(), e);
            return t.prevObject = this, t.context = this.context, t
        },
        each: function (e, t) {
            return ut.each(this, e, t)
        },
        ready: function (e) {
            return ut.ready.promise().done(e), this
        },
        slice: function () {
            return this.pushStack(rt.apply(this, arguments))
        },
        first: function () {
            return this.eq(0)
        },
        last: function () {
            return this.eq(-1)
        },
        eq: function (e) {
            var t = this.length,
                n = +e + (0 > e ? t : 0);
            return this.pushStack(n >= 0 && t > n ? [this[n]] : [])
        },
        map: function (e) {
            return this.pushStack(ut.map(this, function (t, n) {
                return e.call(t, n, t)
            }))
        },
        end: function () {
            return this.prevObject || this.constructor(null)
        },
        push: nt,
        sort: [].sort,
        splice: [].splice
    }, ut.fn.init.prototype = ut.fn, ut.extend = ut.fn.extend = function () {
        var e, n, r, i, o, a, s = arguments[0] || {},
            u = 1,
            l = arguments.length,
            c = !1;
        for ("boolean" == typeof s && (c = s, s = arguments[1] || {}, u = 2), "object" == typeof s || ut.isFunction(s) || (s = {}), l === u && (s = this, --u); l > u; u++) if (null != (o = arguments[u])) for (i in o) e = s[i], r = o[i], s !== r && (c && r && (ut.isPlainObject(r) || (n = ut.isArray(r))) ? (n ? (n = !1, a = e && ut.isArray(e) ? e : []) : a = e && ut.isPlainObject(e) ? e : {}, s[i] = ut.extend(c, a, r)) : r !== t && (s[i] = r));
        return s
    }, ut.extend({
        noConflict: function (t) {
            return e.$ === ut && (e.$ = Q), t && e.jQuery === ut && (e.jQuery = G), ut
        },
        isReady: !1,
        readyWait: 1,
        holdReady: function (e) {
            e ? ut.readyWait++ : ut.ready(!0)
        },
        ready: function (e) {
            if (e === !0 ? !--ut.readyWait : !ut.isReady) {
                if (!Y.body) return setTimeout(ut.ready);
                ut.isReady = !0, e !== !0 && --ut.readyWait > 0 || (X.resolveWith(Y, [ut]), ut.fn.trigger && ut(Y).trigger("ready").off("ready"))
            }
        },
        isFunction: function (e) {
            return "function" === ut.type(e)
        },
        isArray: Array.isArray ||
        function (e) {
            return "array" === ut.type(e)
        },
        isWindow: function (e) {
            return null != e && e == e.window
        },
        isNumeric: function (e) {
            return !isNaN(parseFloat(e)) && isFinite(e)
        },
        type: function (e) {
            return null == e ? e + "" : "object" == typeof e || "function" == typeof e ? K[ot.call(e)] || "object" : typeof e
        },
        isPlainObject: function (e) {
            if (!e || "object" !== ut.type(e) || e.nodeType || ut.isWindow(e)) return !1;
            try {
                if (e.constructor && !at.call(e, "constructor") && !at.call(e.constructor.prototype, "isPrototypeOf")) return !1
            } catch (n) {
                return !1
            }
            var r;
            for (r in e);
            return r === t || at.call(e, r)
        },
        isEmptyObject: function (e) {
            var t;
            for (t in e) return !1;
            return !0
        },
        error: function (e) {
            throw Error(e)
        },
        parseHTML: function (e, t, n) {
            if (!e || "string" != typeof e) return null;
            "boolean" == typeof t && (n = t, t = !1), t = t || Y;
            var r = dt.exec(e),
                i = !n && [];
            return r ? [t.createElement(r[1])] : (r = ut.buildFragment([e], t, i), i && ut(i).remove(), ut.merge([], r.childNodes))
        },
        parseJSON: function (n) {
            return e.JSON && e.JSON.parse ? e.JSON.parse(n) : null === n ? n : "string" == typeof n && (n = ut.trim(n), n && ht.test(n.replace(mt, "@").replace(yt, "]").replace(gt, ""))) ? Function("return " + n)() : (ut.error("Invalid JSON: " + n), t)
        },
        parseXML: function (n) {
            var r, i;
            if (!n || "string" != typeof n) return null;
            try {
                e.DOMParser ? (i = new DOMParser, r = i.parseFromString(n, "text/xml")) : (r = new ActiveXObject("Microsoft.XMLDOM"), r.async = "false", r.loadXML(n))
            } catch (o) {
                r = t
            }
            return r && r.documentElement && !r.getElementsByTagName("parsererror").length || ut.error("Invalid XML: " + n), r
        },
        noop: function () {
        },
        globalEval: function (t) {
            t && ut.trim(t) && (e.execScript ||
            function (t) {
                e.eval.call(e, t)
            })(t)
        },
        camelCase: function (e) {
            return e.replace(vt, "ms-").replace(bt, xt)
        },
        nodeName: function (e, t) {
            return e.nodeName && e.nodeName.toLowerCase() === t.toLowerCase()
        },
        each: function (e, t, r) {
            var i, o = 0,
                a = e.length,
                s = n(e);
            if (r) {
                if (s) for (; a > o && (i = t.apply(e[o], r), i !== !1); o++);
                else for (o in e) if (i = t.apply(e[o], r), i === !1) break
            } else if (s) for (; a > o && (i = t.call(e[o], o, e[o]), i !== !1); o++);
            else for (o in e) if (i = t.call(e[o], o, e[o]), i === !1) break;
            return e
        },
        trim: st && !st.call("锘柯�") ?
            function (e) {
                return null == e ? "" : st.call(e)
            } : function (e) {
            return null == e ? "" : (e + "").replace(ft, "")
        },
        makeArray: function (e, t) {
            var r = t || [];
            return null != e && (n(Object(e)) ? ut.merge(r, "string" == typeof e ? [e] : e) : nt.call(r, e)), r
        },
        inArray: function (e, t, n) {
            var r;
            if (t) {
                if (it) return it.call(t, e, n);
                for (r = t.length, n = n ? 0 > n ? Math.max(0, r + n) : n : 0; r > n; n++) if (n in t && t[n] === e) return n
            }
            return -1
        },
        merge: function (e, n) {
            var r = n.length,
                i = e.length,
                o = 0;
            if ("number" == typeof r) for (; r > o; o++) e[i++] = n[o];
            else for (; n[o] !== t;) e[i++] = n[o++];
            return e.length = i, e
        },
        grep: function (e, t, n) {
            var r, i = [],
                o = 0,
                a = e.length;
            for (n = !!n; a > o; o++) r = !!t(e[o], o), n !== r && i.push(e[o]);
            return i
        },
        map: function (e, t, r) {
            var i, o = 0,
                a = e.length,
                s = n(e),
                u = [];
            if (s) for (; a > o; o++) i = t(e[o], o, r), null != i && (u[u.length] = i);
            else for (o in e) i = t(e[o], o, r), null != i && (u[u.length] = i);
            return tt.apply([], u)
        },
        guid: 1,
        proxy: function (e, n) {
            var r, i, o;
            return "string" == typeof n && (o = e[n], n = e, e = o), ut.isFunction(e) ? (r = rt.call(arguments, 2), i = function () {
                return e.apply(n || this, r.concat(rt.call(arguments)))
            }, i.guid = e.guid = e.guid || ut.guid++, i) : t
        },
        access: function (e, n, r, i, o, a, s) {
            var u = 0,
                l = e.length,
                c = null == r;
            if ("object" === ut.type(r)) {
                o = !0;
                for (u in r) ut.access(e, n, u, r[u], !0, a, s)
            } else if (i !== t && (o = !0, ut.isFunction(i) || (s = !0), c && (s ? (n.call(e, i), n = null) : (c = n, n = function (e, t, n) {
                    return c.call(ut(e), n)
                })), n)) for (; l > u; u++) n(e[u], r, s ? i : i.call(e[u], u, n(e[u], r)));
            return o ? e : c ? n.call(e) : l ? n(e[0], r) : a
        },
        now: function () {
            return (new Date).getTime()
        }
    }), ut.ready.promise = function (t) {
        if (!X) if (X = ut.Deferred(), "complete" === Y.readyState) setTimeout(ut.ready);
        else if (Y.addEventListener) Y.addEventListener("DOMContentLoaded", Tt, !1), e.addEventListener("load", Tt, !1);
        else {
            Y.attachEvent("onreadystatechange", Tt), e.attachEvent("onload", Tt);
            var n = !1;
            try {
                n = null == e.frameElement && Y.documentElement
            } catch (r) {
            }
            n && n.doScroll &&
            function i() {
                if (!ut.isReady) {
                    try {
                        n.doScroll("left")
                    } catch (e) {
                        return setTimeout(i, 50)
                    }
                    wt(), ut.ready()
                }
            }()
        }
        return X.promise(t)
    }, ut.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function (e, t) {
        K["[object " + t + "]"] = t.toLowerCase()
    }), U = ut(Y);
    var Nt = {};
    ut.Callbacks = function (e) {
        e = "string" == typeof e ? Nt[e] || r(e) : ut.extend({}, e);
        var n, i, o, a, s, u, l = [],
            c = !e.once && [],
            f = function (t) {
                for (i = e.memory && t, o = !0, s = u || 0, u = 0, a = l.length, n = !0; l && a > s; s++) if (l[s].apply(t[0], t[1]) === !1 && e.stopOnFalse) {
                    i = !1;
                    break
                }
                n = !1, l && (c ? c.length && f(c.shift()) : i ? l = [] : p.disable())
            },
            p = {
                add: function () {
                    if (l) {
                        var t = l.length;
                        !
                            function r(t) {
                                ut.each(t, function (t, n) {
                                    var i = ut.type(n);
                                    "function" === i ? e.unique && p.has(n) || l.push(n) : n && n.length && "string" !== i && r(n)
                                })
                            }(arguments), n ? a = l.length : i && (u = t, f(i))
                    }
                    return this
                },
                remove: function () {
                    return l && ut.each(arguments, function (e, t) {
                        for (var r;
                             (r = ut.inArray(t, l, r)) > -1;) l.splice(r, 1), n && (a >= r && a--, s >= r && s--)
                    }), this
                },
                has: function (e) {
                    return e ? ut.inArray(e, l) > -1 : !(!l || !l.length)
                },
                empty: function () {
                    return l = [], this
                },
                disable: function () {
                    return l = c = i = t, this
                },
                disabled: function () {
                    return !l
                },
                lock: function () {
                    return c = t, i || p.disable(), this
                },
                locked: function () {
                    return !c
                },
                fireWith: function (e, t) {
                    return t = t || [], t = [e, t.slice ? t.slice() : t], !l || o && !c || (n ? c.push(t) : f(t)), this
                },
                fire: function () {
                    return p.fireWith(this, arguments), this
                },
                fired: function () {
                    return !!o
                }
            };
        return p
    }, ut.extend({
        Deferred: function (e) {
            var t = [
                    ["resolve", "done", ut.Callbacks("once memory"), "resolved"],
                    ["reject", "fail", ut.Callbacks("once memory"), "rejected"],
                    ["notify", "progress", ut.Callbacks("memory")]
                ],
                n = "pending",
                r = {
                    state: function () {
                        return n
                    },
                    always: function () {
                        return i.done(arguments).fail(arguments), this
                    },
                    then: function () {
                        var e = arguments;
                        return ut.Deferred(function (n) {
                            ut.each(t, function (t, o) {
                                var a = o[0],
                                    s = ut.isFunction(e[t]) && e[t];
                                i[o[1]](function () {
                                    var e = s && s.apply(this, arguments);
                                    e && ut.isFunction(e.promise) ? e.promise().done(n.resolve).fail(n.reject).progress(n.notify) : n[a + "With"](this === r ? n.promise() : this, s ? [e] : arguments)
                                })
                            }), e = null
                        }).promise()
                    },
                    promise: function (e) {
                        return null != e ? ut.extend(e, r) : r
                    }
                },
                i = {};
            return r.pipe = r.then, ut.each(t, function (e, o) {
                var a = o[2],
                    s = o[3];
                r[o[1]] = a.add, s && a.add(function () {
                    n = s
                }, t[1 ^ e][2].disable, t[2][2].lock), i[o[0]] = function () {
                    return i[o[0] + "With"](this === i ? r : this, arguments), this
                }, i[o[0] + "With"] = a.fireWith
            }), r.promise(i), e && e.call(i, i), i
        },
        when: function (e) {
            var t, n, r, i = 0,
                o = rt.call(arguments),
                a = o.length,
                s = 1 !== a || e && ut.isFunction(e.promise) ? a : 0,
                u = 1 === s ? e : ut.Deferred(),
                l = function (e, n, r) {
                    return function (i) {
                        n[e] = this, r[e] = arguments.length > 1 ? rt.call(arguments) : i, r === t ? u.notifyWith(n, r) : --s || u.resolveWith(n, r)
                    }
                };
            if (a > 1) for (t = Array(a), n = Array(a), r = Array(a); a > i; i++) o[i] && ut.isFunction(o[i].promise) ? o[i].promise().done(l(i, r, o)).fail(u.reject).progress(l(i, n, t)) : --s;
            return s || u.resolveWith(r, o), u.promise()
        }
    }), ut.support = function () {
        var t, n, r, i, o, a, s, u, l, c, f = Y.createElement("div");
        if (f.setAttribute("className", "t"), f.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", n = f.getElementsByTagName("*"), r = f.getElementsByTagName("a")[0], !n || !r || !n.length) return {};
        o = Y.createElement("select"), s = o.appendChild(Y.createElement("option")), i = f.getElementsByTagName("input")[0], r.style.cssText = "top:1px;float:left;opacity:.5", t = {
            getSetAttribute: "t" !== f.className,
            leadingWhitespace: 3 === f.firstChild.nodeType,
            tbody: !f.getElementsByTagName("tbody").length,
            htmlSerialize: !!f.getElementsByTagName("link").length,
            style: /top/.test(r.getAttribute("style")),
            hrefNormalized: "/a" === r.getAttribute("href"),
            opacity: /^0.5/.test(r.style.opacity),
            cssFloat: !!r.style.cssFloat,
            checkOn: !!i.value,
            optSelected: s.selected,
            enctype: !!Y.createElement("form").enctype,
            html5Clone: "<:nav></:nav>" !== Y.createElement("nav").cloneNode(!0).outerHTML,
            boxModel: "CSS1Compat" === Y.compatMode,
            deleteExpando: !0,
            noCloneEvent: !0,
            inlineBlockNeedsLayout: !1,
            shrinkWrapBlocks: !1,
            reliableMarginRight: !0,
            boxSizingReliable: !0,
            pixelPosition: !1
        }, i.checked = !0, t.noCloneChecked = i.cloneNode(!0).checked, o.disabled = !0, t.optDisabled = !s.disabled;
        try {
            delete f.test
        } catch (p) {
            t.deleteExpando = !1
        }
        i = Y.createElement("input"), i.setAttribute("value", ""), t.input = "" === i.getAttribute("value"), i.value = "t", i.setAttribute("type", "radio"), t.radioValue = "t" === i.value, i.setAttribute("checked", "t"), i.setAttribute("name", "t"), a = Y.createDocumentFragment(), a.appendChild(i), t.appendChecked = i.checked, t.checkClone = a.cloneNode(!0).cloneNode(!0).lastChild.checked, f.attachEvent && (f.attachEvent("onclick", function () {
            t.noCloneEvent = !1
        }), f.cloneNode(!0).click());
        for (c in {
            submit: !0,
            change: !0,
            focusin: !0
        }) f.setAttribute(u = "on" + c, "t"), t[c + "Bubbles"] = u in e || f.attributes[u].expando === !1;
        return f.style.backgroundClip = "content-box", f.cloneNode(!0).style.backgroundClip = "", t.clearCloneStyle = "content-box" === f.style.backgroundClip, ut(function () {
            var n, r, i, o = "padding:0;margin:0;border:0;display:block;box-sizing:content-box;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;",
                a = Y.getElementsByTagName("body")[0];
            a && (n = Y.createElement("div"), n.style.cssText = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px;margin-top:1px", a.appendChild(n).appendChild(f), f.innerHTML = "<table><tr><td></td><td>t</td></tr></table>", i = f.getElementsByTagName("td"), i[0].style.cssText = "padding:0;margin:0;border:0;display:none", l = 0 === i[0].offsetHeight, i[0].style.display = "", i[1].style.display = "none", t.reliableHiddenOffsets = l && 0 === i[0].offsetHeight, f.innerHTML = "", f.style.cssText = "box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;padding:1px;border:1px;display:block;width:4px;margin-top:1%;position:absolute;top:1%;", t.boxSizing = 4 === f.offsetWidth, t.doesNotIncludeMarginInBodyOffset = 1 !== a.offsetTop, e.getComputedStyle && (t.pixelPosition = "1%" !== (e.getComputedStyle(f, null) || {}).top, t.boxSizingReliable = "4px" === (e.getComputedStyle(f, null) || {
                    width: "4px"
                }).width, r = f.appendChild(Y.createElement("div")), r.style.cssText = f.style.cssText = o, r.style.marginRight = r.style.width = "0", f.style.width = "1px", t.reliableMarginRight = !parseFloat((e.getComputedStyle(r, null) || {}).marginRight)), typeof f.style.zoom !== V && (f.innerHTML = "", f.style.cssText = o + "width:1px;padding:1px;display:inline;zoom:1", t.inlineBlockNeedsLayout = 3 === f.offsetWidth, f.style.display = "block", f.innerHTML = "<div></div>", f.firstChild.style.width = "5px", t.shrinkWrapBlocks = 3 !== f.offsetWidth, t.inlineBlockNeedsLayout && (a.style.zoom = 1)), a.removeChild(n), n = f = i = r = null)
        }), n = o = a = s = r = i = null, t
    }();
    var Ct = /(?:\{[\s\S]*\}|\[[\s\S]*\])$/,
        kt = /([A-Z])/g;
    ut.extend({
        cache: {},
        expando: "jQuery" + (et + Math.random()).replace(/\D/g, ""),
        noData: {
            embed: !0,
            object: "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000",
            applet: !0
        },
        hasData: function (e) {
            return e = e.nodeType ? ut.cache[e[ut.expando]] : e[ut.expando], !!e && !s(e)
        },
        data: function (e, t, n) {
            return i(e, t, n)
        },
        removeData: function (e, t) {
            return o(e, t)
        },
        _data: function (e, t, n) {
            return i(e, t, n, !0)
        },
        _removeData: function (e, t) {
            return o(e, t, !0)
        },
        acceptData: function (e) {
            if (e.nodeType && 1 !== e.nodeType && 9 !== e.nodeType) return !1;
            var t = e.nodeName && ut.noData[e.nodeName.toLowerCase()];
            return !t || t !== !0 && e.getAttribute("classid") === t
        }
    }), ut.fn.extend({
        data: function (e, n) {
            var r, i, o = this[0],
                s = 0,
                u = null;
            if (e === t) {
                if (this.length && (u = ut.data(o), 1 === o.nodeType && !ut._data(o, "parsedAttrs"))) {
                    for (r = o.attributes; r.length > s; s++) i = r[s].name, i.indexOf("data-") || (i = ut.camelCase(i.slice(5)), a(o, i, u[i]));
                    ut._data(o, "parsedAttrs", !0)
                }
                return u
            }
            return "object" == typeof e ? this.each(function () {
                ut.data(this, e)
            }) : ut.access(this, function (n) {
                return n === t ? o ? a(o, e, ut.data(o, e)) : null : (this.each(function () {
                    ut.data(this, e, n)
                }), t)
            }, null, n, arguments.length > 1, null, !0)
        },
        removeData: function (e) {
            return this.each(function () {
                ut.removeData(this, e)
            })
        }
    }), ut.extend({
        queue: function (e, n, r) {
            var i;
            return e ? (n = (n || "fx") + "queue", i = ut._data(e, n), r && (!i || ut.isArray(r) ? i = ut._data(e, n, ut.makeArray(r)) : i.push(r)), i || []) : t
        },
        dequeue: function (e, t) {
            t = t || "fx";
            var n = ut.queue(e, t),
                r = n.length,
                i = n.shift(),
                o = ut._queueHooks(e, t),
                a = function () {
                    ut.dequeue(e, t)
                };
            "inprogress" === i && (i = n.shift(), r--), o.cur = i, i && ("fx" === t && n.unshift("inprogress"), delete o.stop, i.call(e, a, o)), !r && o && o.empty.fire()
        },
        _queueHooks: function (e, t) {
            var n = t + "queueHooks";
            return ut._data(e, n) || ut._data(e, n, {
                    empty: ut.Callbacks("once memory").add(function () {
                        ut._removeData(e, t + "queue"), ut._removeData(e, n)
                    })
                })
        }
    }), ut.fn.extend({
        queue: function (e, n) {
            var r = 2;
            return "string" != typeof e && (n = e, e = "fx", r--), r > arguments.length ? ut.queue(this[0], e) : n === t ? this : this.each(function () {
                var t = ut.queue(this, e, n);
                ut._queueHooks(this, e), "fx" === e && "inprogress" !== t[0] && ut.dequeue(this, e)
            })
        },
        dequeue: function (e) {
            return this.each(function () {
                ut.dequeue(this, e)
            })
        },
        delay: function (e, t) {
            return e = ut.fx ? ut.fx.speeds[e] || e : e, t = t || "fx", this.queue(t, function (t, n) {
                var r = setTimeout(t, e);
                n.stop = function () {
                    clearTimeout(r)
                }
            })
        },
        clearQueue: function (e) {
            return this.queue(e || "fx", [])
        },
        promise: function (e, n) {
            var r, i = 1,
                o = ut.Deferred(),
                a = this,
                s = this.length,
                u = function () {
                    --i || o.resolveWith(a, [a])
                };
            for ("string" != typeof e && (n = e, e = t), e = e || "fx"; s--;) r = ut._data(a[s], e + "queueHooks"), r && r.empty && (i++, r.empty.add(u));
            return u(), o.promise(n)
        }
    });
    var Et, St, At = /[\t\r\n]/g,
        jt = /\r/g,
        Dt = /^(?:input|select|textarea|button|object)$/i,
        Lt = /^(?:a|area)$/i,
        Ht = /^(?:checked|selected|autofocus|autoplay|async|controls|defer|disabled|hidden|loop|multiple|open|readonly|required|scoped)$/i,
        qt = /^(?:checked|selected)$/i,
        Mt = ut.support.getSetAttribute,
        _t = ut.support.input;
    ut.fn.extend({
        attr: function (e, t) {
            return ut.access(this, ut.attr, e, t, arguments.length > 1)
        },
        removeAttr: function (e) {
            return this.each(function () {
                ut.removeAttr(this, e)
            })
        },
        prop: function (e, t) {
            return ut.access(this, ut.prop, e, t, arguments.length > 1)
        },
        removeProp: function (e) {
            return e = ut.propFix[e] || e, this.each(function () {
                try {
                    this[e] = t, delete this[e]
                } catch (n) {
                }
            })
        },
        addClass: function (e) {
            var t, n, r, i, o, a = 0,
                s = this.length,
                u = "string" == typeof e && e;
            if (ut.isFunction(e)) return this.each(function (t) {
                ut(this).addClass(e.call(this, t, this.className))
            });
            if (u) for (t = (e || "").match(ct) || []; s > a; a++) if (n = this[a], r = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(At, " ") : " ")) {
                for (o = 0; i = t[o++];) 0 > r.indexOf(" " + i + " ") && (r += i + " ");
                n.className = ut.trim(r)
            }
            return this
        },
        removeClass: function (e) {
            var t, n, r, i, o, a = 0,
                s = this.length,
                u = 0 === arguments.length || "string" == typeof e && e;
            if (ut.isFunction(e)) return this.each(function (t) {
                ut(this).removeClass(e.call(this, t, this.className))
            });
            if (u) for (t = (e || "").match(ct) || []; s > a; a++) if (n = this[a], r = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(At, " ") : "")) {
                for (o = 0; i = t[o++];) for (; r.indexOf(" " + i + " ") >= 0;) r = r.replace(" " + i + " ", " ");
                n.className = e ? ut.trim(r) : ""
            }
            return this
        },
        toggleClass: function (e, t) {
            var n = typeof e,
                r = "boolean" == typeof t;
            return this.each(ut.isFunction(e) ?
                function (n) {
                    ut(this).toggleClass(e.call(this, n, this.className, t), t)
                } : function () {
                if ("string" === n) for (var i, o = 0, a = ut(this), s = t, u = e.match(ct) || []; i = u[o++];) s = r ? s : !a.hasClass(i), a[s ? "addClass" : "removeClass"](i);
                else(n === V || "boolean" === n) && (this.className && ut._data(this, "__className__", this.className), this.className = this.className || e === !1 ? "" : ut._data(this, "__className__") || "")
            })
        },
        hasClass: function (e) {
            for (var t = " " + e + " ", n = 0, r = this.length; r > n; n++) if (1 === this[n].nodeType && (" " + this[n].className + " ").replace(At, " ").indexOf(t) >= 0) return !0;
            return !1
        },
        val: function (e) {
            var n, r, i, o = this[0];
            return arguments.length ? (i = ut.isFunction(e), this.each(function (n) {
                var o, a = ut(this);
                1 === this.nodeType && (o = i ? e.call(this, n, a.val()) : e, null == o ? o = "" : "number" == typeof o ? o += "" : ut.isArray(o) && (o = ut.map(o, function (e) {
                    return null == e ? "" : e + ""
                })), r = ut.valHooks[this.type] || ut.valHooks[this.nodeName.toLowerCase()], r && "set" in r && r.set(this, o, "value") !== t || (this.value = o))
            })) : o ? (r = ut.valHooks[o.type] || ut.valHooks[o.nodeName.toLowerCase()], r && "get" in r && (n = r.get(o, "value")) !== t ? n : (n = o.value, "string" == typeof n ? n.replace(jt, "") : null == n ? "" : n)) : void 0
        }
    }), ut.extend({
        valHooks: {
            option: {
                get: function (e) {
                    var t = e.attributes.value;
                    return !t || t.specified ? e.value : e.text
                }
            },
            select: {
                get: function (e) {
                    for (var t, n, r = e.options, i = e.selectedIndex, o = "select-one" === e.type || 0 > i, a = o ? null : [], s = o ? i + 1 : r.length, u = 0 > i ? s : o ? i : 0; s > u; u++) if (n = r[u], !(!n.selected && u !== i || (ut.support.optDisabled ? n.disabled : null !== n.getAttribute("disabled")) || n.parentNode.disabled && ut.nodeName(n.parentNode, "optgroup"))) {
                        if (t = ut(n).val(), o) return t;
                        a.push(t)
                    }
                    return a
                },
                set: function (e, t) {
                    var n = ut.makeArray(t);
                    return ut(e).find("option").each(function () {
                        this.selected = ut.inArray(ut(this).val(), n) >= 0
                    }), n.length || (e.selectedIndex = -1), n
                }
            }
        },
        attr: function (e, n, r) {
            var i, o, a, s = e.nodeType;
            return e && 3 !== s && 8 !== s && 2 !== s ? typeof e.getAttribute === V ? ut.prop(e, n, r) : (o = 1 !== s || !ut.isXMLDoc(e), o && (n = n.toLowerCase(), i = ut.attrHooks[n] || (Ht.test(n) ? St : Et)), r === t ? i && o && "get" in i && null !== (a = i.get(e, n)) ? a : (typeof e.getAttribute !== V && (a = e.getAttribute(n)), null == a ? t : a) : null !== r ? i && o && "set" in i && (a = i.set(e, r, n)) !== t ? a : (e.setAttribute(n, r + ""), r) : (ut.removeAttr(e, n), t)) : void 0
        },
        removeAttr: function (e, t) {
            var n, r, i = 0,
                o = t && t.match(ct);
            if (o && 1 === e.nodeType) for (; n = o[i++];) r = ut.propFix[n] || n, Ht.test(n) ? !Mt && qt.test(n) ? e[ut.camelCase("default-" + n)] = e[r] = !1 : e[r] = !1 : ut.attr(e, n, ""), e.removeAttribute(Mt ? n : r)
        },
        attrHooks: {
            type: {
                set: function (e, t) {
                    if (!ut.support.radioValue && "radio" === t && ut.nodeName(e, "input")) {
                        var n = e.value;
                        return e.setAttribute("type", t), n && (e.value = n), t
                    }
                }
            }
        },
        propFix: {
            tabindex: "tabIndex",
            readonly: "readOnly",
            "for": "htmlFor",
            "class": "className",
            maxlength: "maxLength",
            cellspacing: "cellSpacing",
            cellpadding: "cellPadding",
            rowspan: "rowSpan",
            colspan: "colSpan",
            usemap: "useMap",
            frameborder: "frameBorder",
            contenteditable: "contentEditable"
        },
        prop: function (e, n, r) {
            var i, o, a, s = e.nodeType;
            return e && 3 !== s && 8 !== s && 2 !== s ? (a = 1 !== s || !ut.isXMLDoc(e), a && (n = ut.propFix[n] || n, o = ut.propHooks[n]), r !== t ? o && "set" in o && (i = o.set(e, r, n)) !== t ? i : e[n] = r : o && "get" in o && null !== (i = o.get(e, n)) ? i : e[n]) : void 0
        },
        propHooks: {
            tabIndex: {
                get: function (e) {
                    var n = e.getAttributeNode("tabindex");
                    return n && n.specified ? parseInt(n.value, 10) : Dt.test(e.nodeName) || Lt.test(e.nodeName) && e.href ? 0 : t
                }
            }
        }
    }), St = {
        get: function (e, n) {
            var r = ut.prop(e, n),
                i = "boolean" == typeof r && e.getAttribute(n),
                o = "boolean" == typeof r ? _t && Mt ? null != i : qt.test(n) ? e[ut.camelCase("default-" + n)] : !!i : e.getAttributeNode(n);
            return o && o.value !== !1 ? n.toLowerCase() : t
        },
        set: function (e, t, n) {
            return t === !1 ? ut.removeAttr(e, n) : _t && Mt || !qt.test(n) ? e.setAttribute(!Mt && ut.propFix[n] || n, n) : e[ut.camelCase("default-" + n)] = e[n] = !0, n
        }
    }, _t && Mt || (ut.attrHooks.value = {
        get: function (e, n) {
            var r = e.getAttributeNode(n);
            return ut.nodeName(e, "input") ? e.defaultValue : r && r.specified ? r.value : t
        },
        set: function (e, n, r) {
            return ut.nodeName(e, "input") ? (e.defaultValue = n, t) : Et && Et.set(e, n, r)
        }
    }), Mt || (Et = ut.valHooks.button = {
        get: function (e, n) {
            var r = e.getAttributeNode(n);
            return r && ("id" === n || "name" === n || "coords" === n ? "" !== r.value : r.specified) ? r.value : t
        },
        set: function (e, n, r) {
            var i = e.getAttributeNode(r);
            return i || e.setAttributeNode(i = e.ownerDocument.createAttribute(r)), i.value = n += "", "value" === r || n === e.getAttribute(r) ? n : t
        }
    }, ut.attrHooks.contenteditable = {
        get: Et.get,
        set: function (e, t, n) {
            Et.set(e, "" === t ? !1 : t, n)
        }
    }, ut.each(["width", "height"], function (e, n) {
        ut.attrHooks[n] = ut.extend(ut.attrHooks[n], {
            set: function (e, r) {
                return "" === r ? (e.setAttribute(n, "auto"), r) : t
            }
        })
    })), ut.support.hrefNormalized || (ut.each(["href", "src", "width", "height"], function (e, n) {
        ut.attrHooks[n] = ut.extend(ut.attrHooks[n], {
            get: function (e) {
                var r = e.getAttribute(n, 2);
                return null == r ? t : r
            }
        })
    }), ut.each(["href", "src"], function (e, t) {
        ut.propHooks[t] = {
            get: function (e) {
                return e.getAttribute(t, 4)
            }
        }
    })), ut.support.style || (ut.attrHooks.style = {
        get: function (e) {
            return e.style.cssText || t
        },
        set: function (e, t) {
            return e.style.cssText = t + ""
        }
    }), ut.support.optSelected || (ut.propHooks.selected = ut.extend(ut.propHooks.selected, {
        get: function (e) {
            var t = e.parentNode;
            return t && (t.selectedIndex, t.parentNode && t.parentNode.selectedIndex), null
        }
    })), ut.support.enctype || (ut.propFix.enctype = "encoding"), ut.support.checkOn || ut.each(["radio", "checkbox"], function () {
        ut.valHooks[this] = {
            get: function (e) {
                return null === e.getAttribute("value") ? "on" : e.value
            }
        }
    }), ut.each(["radio", "checkbox"], function () {
        ut.valHooks[this] = ut.extend(ut.valHooks[this], {
            set: function (e, n) {
                return ut.isArray(n) ? e.checked = ut.inArray(ut(e).val(), n) >= 0 : t
            }
        })
    });
    var Ft = /^(?:input|select|textarea)$/i,
        Ot = /^key/,
        Bt = /^(?:mouse|contextmenu)|click/,
        Pt = /^(?:focusinfocus|focusoutblur)$/,
        Rt = /^([^.]*)(?:\.(.+)|)$/;
    ut.event = {
        global: {},
        add: function (e, n, r, i, o) {
            var a, s, u, l, c, f, p, d, h, g, m, y = ut._data(e);
            if (y) {
                for (r.handler && (l = r, r = l.handler, o = l.selector), r.guid || (r.guid = ut.guid++), (s = y.events) || (s = y.events = {}), (f = y.handle) || (f = y.handle = function (e) {
                    return typeof ut === V || e && ut.event.triggered === e.type ? t : ut.event.dispatch.apply(f.elem, arguments)
                }, f.elem = e), n = (n || "").match(ct) || [""], u = n.length; u--;) a = Rt.exec(n[u]) || [], h = m = a[1], g = (a[2] || "").split(".").sort(), c = ut.event.special[h] || {}, h = (o ? c.delegateType : c.bindType) || h, c = ut.event.special[h] || {}, p = ut.extend({
                    type: h,
                    origType: m,
                    data: i,
                    handler: r,
                    guid: r.guid,
                    selector: o,
                    needsContext: o && ut.expr.match.needsContext.test(o),
                    namespace: g.join(".")
                }, l), (d = s[h]) || (d = s[h] = [], d.delegateCount = 0, c.setup && c.setup.call(e, i, g, f) !== !1 || (e.addEventListener ? e.addEventListener(h, f, !1) : e.attachEvent && e.attachEvent("on" + h, f))), c.add && (c.add.call(e, p), p.handler.guid || (p.handler.guid = r.guid)), o ? d.splice(d.delegateCount++, 0, p) : d.push(p), ut.event.global[h] = !0;
                e = null
            }
        },
        remove: function (e, t, n, r, i) {
            var o, a, s, u, l, c, f, p, d, h, g, m = ut.hasData(e) && ut._data(e);
            if (m && (c = m.events)) {
                for (t = (t || "").match(ct) || [""], l = t.length; l--;) if (s = Rt.exec(t[l]) || [], d = g = s[1], h = (s[2] || "").split(".").sort(), d) {
                    for (f = ut.event.special[d] || {}, d = (r ? f.delegateType : f.bindType) || d, p = c[d] || [], s = s[2] && RegExp("(^|\\.)" + h.join("\\.(?:.*\\.|)") + "(\\.|$)"), u = o = p.length; o--;) a = p[o], !i && g !== a.origType || n && n.guid !== a.guid || s && !s.test(a.namespace) || r && r !== a.selector && ("**" !== r || !a.selector) || (p.splice(o, 1), a.selector && p.delegateCount--, f.remove && f.remove.call(e, a));
                    u && !p.length && (f.teardown && f.teardown.call(e, h, m.handle) !== !1 || ut.removeEvent(e, d, m.handle), delete c[d])
                } else for (d in c) ut.event.remove(e, d + t[l], n, r, !0);
                ut.isEmptyObject(c) && (delete m.handle, ut._removeData(e, "events"))
            }
        },
        trigger: function (n, r, i, o) {
            var a, s, u, l, c, f, p, d = [i || Y],
                h = at.call(n, "type") ? n.type : n,
                g = at.call(n, "namespace") ? n.namespace.split(".") : [];
            if (u = f = i = i || Y, 3 !== i.nodeType && 8 !== i.nodeType && !Pt.test(h + ut.event.triggered) && (h.indexOf(".") >= 0 && (g = h.split("."), h = g.shift(), g.sort()), s = 0 > h.indexOf(":") && "on" + h, n = n[ut.expando] ? n : new ut.Event(h, "object" == typeof n && n), n.isTrigger = !0, n.namespace = g.join("."), n.namespace_re = n.namespace ? RegExp("(^|\\.)" + g.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, n.result = t, n.target || (n.target = i), r = null == r ? [n] : ut.makeArray(r, [n]), c = ut.event.special[h] || {}, o || !c.trigger || c.trigger.apply(i, r) !== !1)) {
                if (!o && !c.noBubble && !ut.isWindow(i)) {
                    for (l = c.delegateType || h, Pt.test(l + h) || (u = u.parentNode); u; u = u.parentNode) d.push(u), f = u;
                    f === (i.ownerDocument || Y) && d.push(f.defaultView || f.parentWindow || e)
                }
                for (p = 0;
                     (u = d[p++]) && !n.isPropagationStopped();) n.type = p > 1 ? l : c.bindType || h, a = (ut._data(u, "events") || {})[n.type] && ut._data(u, "handle"), a && a.apply(u, r), a = s && u[s], a && ut.acceptData(u) && a.apply && a.apply(u, r) === !1 && n.preventDefault();
                if (n.type = h, !(o || n.isDefaultPrevented() || c._default && c._default.apply(i.ownerDocument, r) !== !1 || "click" === h && ut.nodeName(i, "a") || !ut.acceptData(i) || !s || !i[h] || ut.isWindow(i))) {
                    f = i[s], f && (i[s] = null), ut.event.triggered = h;
                    try {
                        i[h]()
                    } catch (m) {
                    }
                    ut.event.triggered = t, f && (i[s] = f)
                }
                return n.result
            }
        },
        dispatch: function (e) {
            e = ut.event.fix(e);
            var n, r, i, o, a, s = [],
                u = rt.call(arguments),
                l = (ut._data(this, "events") || {})[e.type] || [],
                c = ut.event.special[e.type] || {};
            if (u[0] = e, e.delegateTarget = this, !c.preDispatch || c.preDispatch.call(this, e) !== !1) {
                for (s = ut.event.handlers.call(this, e, l), n = 0;
                     (o = s[n++]) && !e.isPropagationStopped();) for (e.currentTarget = o.elem, a = 0;
                                                                      (i = o.handlers[a++]) && !e.isImmediatePropagationStopped();)(!e.namespace_re || e.namespace_re.test(i.namespace)) && (e.handleObj = i, e.data = i.data, r = ((ut.event.special[i.origType] || {}).handle || i.handler).apply(o.elem, u), r !== t && (e.result = r) === !1 && (e.preventDefault(), e.stopPropagation()));
                return c.postDispatch && c.postDispatch.call(this, e), e.result
            }
        },
        handlers: function (e, n) {
            var r, i, o, a, s = [],
                u = n.delegateCount,
                l = e.target;
            if (u && l.nodeType && (!e.button || "click" !== e.type)) for (; l != this; l = l.parentNode || this) if (1 === l.nodeType && (l.disabled !== !0 || "click" !== e.type)) {
                for (o = [], a = 0; u > a; a++) i = n[a], r = i.selector + " ", o[r] === t && (o[r] = i.needsContext ? ut(r, this).index(l) >= 0 : ut.find(r, this, null, [l]).length), o[r] && o.push(i);
                o.length && s.push({
                    elem: l,
                    handlers: o
                })
            }
            return n.length > u && s.push({
                elem: this,
                handlers: n.slice(u)
            }), s
        },
        fix: function (e) {
            if (e[ut.expando]) return e;
            var t, n, r, i = e.type,
                o = e,
                a = this.fixHooks[i];
            for (a || (this.fixHooks[i] = a = Bt.test(i) ? this.mouseHooks : Ot.test(i) ? this.keyHooks : {}), r = a.props ? this.props.concat(a.props) : this.props, e = new ut.Event(o), t = r.length; t--;) n = r[t], e[n] = o[n];
            return e.target || (e.target = o.srcElement || Y), 3 === e.target.nodeType && (e.target = e.target.parentNode), e.metaKey = !!e.metaKey, a.filter ? a.filter(e, o) : e
        },
        props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
        fixHooks: {},
        keyHooks: {
            props: "char charCode key keyCode".split(" "),
            filter: function (e, t) {
                return null == e.which && (e.which = null != t.charCode ? t.charCode : t.keyCode), e
            }
        },
        mouseHooks: {
            props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
            filter: function (e, n) {
                var r, i, o, a = n.button,
                    s = n.fromElement;
                return null == e.pageX && null != n.clientX && (i = e.target.ownerDocument || Y, o = i.documentElement, r = i.body, e.pageX = n.clientX + (o && o.scrollLeft || r && r.scrollLeft || 0) - (o && o.clientLeft || r && r.clientLeft || 0), e.pageY = n.clientY + (o && o.scrollTop || r && r.scrollTop || 0) - (o && o.clientTop || r && r.clientTop || 0)), !e.relatedTarget && s && (e.relatedTarget = s === e.target ? n.toElement : s), e.which || a === t || (e.which = 1 & a ? 1 : 2 & a ? 3 : 4 & a ? 2 : 0), e
            }
        },
        special: {
            load: {
                noBubble: !0
            },
            click: {
                trigger: function () {
                    return ut.nodeName(this, "input") && "checkbox" === this.type && this.click ? (this.click(), !1) : t
                }
            },
            focus: {
                trigger: function () {
                    if (this !== Y.activeElement && this.focus) try {
                        return this.focus(), !1
                    } catch (e) {
                    }
                },
                delegateType: "focusin"
            },
            blur: {
                trigger: function () {
                    return this === Y.activeElement && this.blur ? (this.blur(), !1) : t
                },
                delegateType: "focusout"
            },
            beforeunload: {
                postDispatch: function (e) {
                    e.result !== t && (e.originalEvent.returnValue = e.result)
                }
            }
        },
        simulate: function (e, t, n, r) {
            var i = ut.extend(new ut.Event, n, {
                type: e,
                isSimulated: !0,
                originalEvent: {}
            });
            r ? ut.event.trigger(i, null, t) : ut.event.dispatch.call(t, i), i.isDefaultPrevented() && n.preventDefault()
        }
    }, ut.removeEvent = Y.removeEventListener ?
        function (e, t, n) {
            e.removeEventListener && e.removeEventListener(t, n, !1)
        } : function (e, t, n) {
        var r = "on" + t;
        e.detachEvent && (typeof e[r] === V && (e[r] = null), e.detachEvent(r, n))
    }, ut.Event = function (e, n) {
        return this instanceof ut.Event ? (e && e.type ? (this.originalEvent = e, this.type = e.type, this.isDefaultPrevented = e.defaultPrevented || e.returnValue === !1 || e.getPreventDefault && e.getPreventDefault() ? u : l) : this.type = e, n && ut.extend(this, n), this.timeStamp = e && e.timeStamp || ut.now(), this[ut.expando] = !0, t) : new ut.Event(e, n)
    }, ut.Event.prototype = {
        isDefaultPrevented: l,
        isPropagationStopped: l,
        isImmediatePropagationStopped: l,
        preventDefault: function () {
            var e = this.originalEvent;
            this.isDefaultPrevented = u, e && (e.preventDefault ? e.preventDefault() : e.returnValue = !1)
        },
        stopPropagation: function () {
            var e = this.originalEvent;
            this.isPropagationStopped = u, e && (e.stopPropagation && e.stopPropagation(), e.cancelBubble = !0)
        },
        stopImmediatePropagation: function () {
            this.isImmediatePropagationStopped = u, this.stopPropagation()
        }
    }, ut.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout"
    }, function (e, t) {
        ut.event.special[e] = {
            delegateType: t,
            bindType: t,
            handle: function (e) {
                var n, r = this,
                    i = e.relatedTarget,
                    o = e.handleObj;
                return (!i || i !== r && !ut.contains(r, i)) && (e.type = o.origType, n = o.handler.apply(this, arguments), e.type = t), n
            }
        }
    }), ut.support.submitBubbles || (ut.event.special.submit = {
        setup: function () {
            return ut.nodeName(this, "form") ? !1 : (ut.event.add(this, "click._submit keypress._submit", function (e) {
                var n = e.target,
                    r = ut.nodeName(n, "input") || ut.nodeName(n, "button") ? n.form : t;
                r && !ut._data(r, "submitBubbles") && (ut.event.add(r, "submit._submit", function (e) {
                    e._submit_bubble = !0
                }), ut._data(r, "submitBubbles", !0))
            }), t)
        },
        postDispatch: function (e) {
            e._submit_bubble && (delete e._submit_bubble, this.parentNode && !e.isTrigger && ut.event.simulate("submit", this.parentNode, e, !0))
        },
        teardown: function () {
            return ut.nodeName(this, "form") ? !1 : (ut.event.remove(this, "._submit"), t)
        }
    }), ut.support.changeBubbles || (ut.event.special.change = {
        setup: function () {
            return Ft.test(this.nodeName) ? (("checkbox" === this.type || "radio" === this.type) && (ut.event.add(this, "propertychange._change", function (e) {
                "checked" === e.originalEvent.propertyName && (this._just_changed = !0)
            }), ut.event.add(this, "click._change", function (e) {
                this._just_changed && !e.isTrigger && (this._just_changed = !1), ut.event.simulate("change", this, e, !0)
            })), !1) : (ut.event.add(this, "beforeactivate._change", function (e) {
                var t = e.target;
                Ft.test(t.nodeName) && !ut._data(t, "changeBubbles") && (ut.event.add(t, "change._change", function (e) {
                    !this.parentNode || e.isSimulated || e.isTrigger || ut.event.simulate("change", this.parentNode, e, !0)
                }), ut._data(t, "changeBubbles", !0))
            }), t)
        },
        handle: function (e) {
            var n = e.target;
            return this !== n || e.isSimulated || e.isTrigger || "radio" !== n.type && "checkbox" !== n.type ? e.handleObj.handler.apply(this, arguments) : t
        },
        teardown: function () {
            return ut.event.remove(this, "._change"), !Ft.test(this.nodeName)
        }
    }), ut.support.focusinBubbles || ut.each({
        focus: "focusin",
        blur: "focusout"
    }, function (e, t) {
        var n = 0,
            r = function (e) {
                ut.event.simulate(t, e.target, ut.event.fix(e), !0)
            };
        ut.event.special[t] = {
            setup: function () {
                0 === n++ && Y.addEventListener(e, r, !0)
            },
            teardown: function () {
                0 === --n && Y.removeEventListener(e, r, !0)
            }
        }
    }), ut.fn.extend({
        on: function (e, n, r, i, o) {
            var a, s;
            if ("object" == typeof e) {
                "string" != typeof n && (r = r || n, n = t);
                for (a in e) this.on(a, n, r, e[a], o);
                return this
            }
            if (null == r && null == i ? (i = n, r = n = t) : null == i && ("string" == typeof n ? (i = r, r = t) : (i = r, r = n, n = t)), i === !1) i = l;
            else if (!i) return this;
            return 1 === o && (s = i, i = function (e) {
                return ut().off(e), s.apply(this, arguments)
            }, i.guid = s.guid || (s.guid = ut.guid++)), this.each(function () {
                ut.event.add(this, e, i, r, n)
            })
        },
        one: function (e, t, n, r) {
            return this.on(e, t, n, r, 1)
        },
        off: function (e, n, r) {
            var i, o;
            if (e && e.preventDefault && e.handleObj) return i = e.handleObj, ut(e.delegateTarget).off(i.namespace ? i.origType + "." + i.namespace : i.origType, i.selector, i.handler), this;
            if ("object" == typeof e) {
                for (o in e) this.off(o, n, e[o]);
                return this
            }
            return (n === !1 || "function" == typeof n) && (r = n, n = t), r === !1 && (r = l), this.each(function () {
                ut.event.remove(this, e, r, n)
            })
        },
        bind: function (e, t, n) {
            return this.on(e, null, t, n)
        },
        unbind: function (e, t) {
            return this.off(e, null, t)
        },
        delegate: function (e, t, n, r) {
            return this.on(t, e, n, r)
        },
        undelegate: function (e, t, n) {
            return 1 === arguments.length ? this.off(e, "**") : this.off(t, e || "**", n)
        },
        trigger: function (e, t) {
            return this.each(function () {
                ut.event.trigger(e, t, this)
            })
        },
        triggerHandler: function (e, n) {
            var r = this[0];
            return r ? ut.event.trigger(e, n, r, !0) : t
        }
    }), function (e, t) {
        function n(e) {
            return ht.test(e + "")
        }

        function r() {
            var e, t = [];
            return e = function (n, r) {
                return t.push(n += " ") > C.cacheLength && delete e[t.shift()], e[n] = r
            }
        }

        function i(e) {
            return e[P] = !0, e
        }

        function o(e) {
            var t = L.createElement("div");
            try {
                return e(t)
            } catch (n) {
                return !1
            } finally {
                t = null
            }
        }

        function a(e, t, n, r) {
            var i, o, a, s, u, l, c, d, h, g;
            if ((t ? t.ownerDocument || t : R) !== L && D(t), t = t || L, n = n || [], !e || "string" != typeof e) return n;
            if (1 !== (s = t.nodeType) && 9 !== s) return [];
            if (!q && !r) {
                if (i = gt.exec(e)) if (a = i[1]) {
                    if (9 === s) {
                        if (o = t.getElementById(a), !o || !o.parentNode) return n;
                        if (o.id === a) return n.push(o), n
                    } else if (t.ownerDocument && (o = t.ownerDocument.getElementById(a)) && O(t, o) && o.id === a) return n.push(o), n
                } else {
                    if (i[2]) return Q.apply(n, K.call(t.getElementsByTagName(e), 0)), n;
                    if ((a = i[3]) && W.getByClassName && t.getElementsByClassName) return Q.apply(n, K.call(t.getElementsByClassName(a), 0)), n
                }
                if (W.qsa && !M.test(e)) {
                    if (c = !0, d = P, h = t, g = 9 === s && e, 1 === s && "object" !== t.nodeName.toLowerCase()) {
                        for (l = f(e), (c = t.getAttribute("id")) ? d = c.replace(vt, "\\$&") : t.setAttribute("id", d), d = "[id='" + d + "'] ", u = l.length; u--;) l[u] = d + p(l[u]);
                        h = dt.test(e) && t.parentNode || t, g = l.join(",")
                    }
                    if (g) try {
                        return Q.apply(n, K.call(h.querySelectorAll(g), 0)), n
                    } catch (m) {
                    } finally {
                        c || t.removeAttribute("id")
                    }
                }
            }
            return x(e.replace(at, "$1"), t, n, r)
        }

        function s(e, t) {
            var n = t && e,
                r = n && (~t.sourceIndex || Y) - (~e.sourceIndex || Y);
            if (r) return r;
            if (n) for (; n = n.nextSibling;) if (n === t) return -1;
            return e ? 1 : -1
        }

        function u(e) {
            return function (t) {
                var n = t.nodeName.toLowerCase();
                return "input" === n && t.type === e
            }
        }

        function l(e) {
            return function (t) {
                var n = t.nodeName.toLowerCase();
                return ("input" === n || "button" === n) && t.type === e
            }
        }

        function c(e) {
            return i(function (t) {
                return t = +t, i(function (n, r) {
                    for (var i, o = e([], n.length, t), a = o.length; a--;) n[i = o[a]] && (n[i] = !(r[i] = n[i]))
                })
            })
        }

        function f(e, t) {
            var n, r, i, o, s, u, l, c = X[e + " "];
            if (c) return t ? 0 : c.slice(0);
            for (s = e, u = [], l = C.preFilter; s;) {
                (!n || (r = st.exec(s))) && (r && (s = s.slice(r[0].length) || s), u.push(i = [])), n = !1, (r = lt.exec(s)) && (n = r.shift(), i.push({
                    value: n,
                    type: r[0].replace(at, " ")
                }), s = s.slice(n.length));
                for (o in C.filter)!(r = pt[o].exec(s)) || l[o] && !(r = l[o](r)) || (n = r.shift(), i.push({
                    value: n,
                    type: o,
                    matches: r
                }), s = s.slice(n.length));
                if (!n) break
            }
            return t ? s.length : s ? a.error(e) : X(e, u).slice(0)
        }

        function p(e) {
            for (var t = 0, n = e.length, r = ""; n > t; t++) r += e[t].value;
            return r
        }

        function d(e, t, n) {
            var r = t.dir,
                i = n && "parentNode" === r,
                o = I++;
            return t.first ?
                function (t, n, o) {
                    for (; t = t[r];) if (1 === t.nodeType || i) return e(t, n, o)
                } : function (t, n, a) {
                var s, u, l, c = $ + " " + o;
                if (a) {
                    for (; t = t[r];) if ((1 === t.nodeType || i) && e(t, n, a)) return !0
                } else for (; t = t[r];) if (1 === t.nodeType || i) if (l = t[P] || (t[P] = {}), (u = l[r]) && u[0] === c) {
                    if ((s = u[1]) === !0 || s === N) return s === !0
                } else if (u = l[r] = [c], u[1] = e(t, n, a) || N, u[1] === !0) return !0
            }
        }

        function h(e) {
            return e.length > 1 ?
                function (t, n, r) {
                    for (var i = e.length; i--;) if (!e[i](t, n, r)) return !1;
                    return !0
                } : e[0]
        }

        function g(e, t, n, r, i) {
            for (var o, a = [], s = 0, u = e.length, l = null != t; u > s; s++)(o = e[s]) && (!n || n(o, r, i)) && (a.push(o), l && t.push(s));
            return a
        }

        function m(e, t, n, r, o, a) {
            return r && !r[P] && (r = m(r)), o && !o[P] && (o = m(o, a)), i(function (i, a, s, u) {
                var l, c, f, p = [],
                    d = [],
                    h = a.length,
                    m = i || b(t || "*", s.nodeType ? [s] : s, []),
                    y = !e || !i && t ? m : g(m, p, e, s, u),
                    v = n ? o || (i ? e : h || r) ? [] : a : y;
                if (n && n(y, v, s, u), r) for (l = g(v, d), r(l, [], s, u), c = l.length; c--;)(f = l[c]) && (v[d[c]] = !(y[d[c]] = f));
                if (i) {
                    if (o || e) {
                        if (o) {
                            for (l = [], c = v.length; c--;)(f = v[c]) && l.push(y[c] = f);
                            o(null, v = [], l, u)
                        }
                        for (c = v.length; c--;)(f = v[c]) && (l = o ? Z.call(i, f) : p[c]) > -1 && (i[l] = !(a[l] = f))
                    }
                } else v = g(v === a ? v.splice(h, v.length) : v), o ? o(null, a, v, u) : Q.apply(a, v)
            })
        }

        function y(e) {
            for (var t, n, r, i = e.length, o = C.relative[e[0].type], a = o || C.relative[" "], s = o ? 1 : 0, u = d(function (e) {
                return e === t
            }, a, !0), l = d(function (e) {
                return Z.call(t, e) > -1
            }, a, !0), c = [function (e, n, r) {
                return !o && (r || n !== j) || ((t = n).nodeType ? u(e, n, r) : l(e, n, r))
            }]; i > s; s++) if (n = C.relative[e[s].type]) c = [d(h(c), n)];
            else {
                if (n = C.filter[e[s].type].apply(null, e[s].matches), n[P]) {
                    for (r = ++s; i > r && !C.relative[e[r].type]; r++);
                    return m(s > 1 && h(c), s > 1 && p(e.slice(0, s - 1)).replace(at, "$1"), n, r > s && y(e.slice(s, r)), i > r && y(e = e.slice(r)), i > r && p(e))
                }
                c.push(n)
            }
            return h(c)
        }

        function v(e, t) {
            var n = 0,
                r = t.length > 0,
                o = e.length > 0,
                s = function (i, s, u, l, c) {
                    var f, p, d, h = [],
                        m = 0,
                        y = "0",
                        v = i && [],
                        b = null != c,
                        x = j,
                        T = i || o && C.find.TAG("*", c && s.parentNode || s),
                        w = $ += null == x ? 1 : Math.random() || .1;
                    for (b && (j = s !== L && s, N = n); null != (f = T[y]); y++) {
                        if (o && f) {
                            for (p = 0; d = e[p++];) if (d(f, s, u)) {
                                l.push(f);
                                break
                            }
                            b && ($ = w, N = ++n)
                        }
                        r && ((f = !d && f) && m--, i && v.push(f))
                    }
                    if (m += y, r && y !== m) {
                        for (p = 0; d = t[p++];) d(v, h, s, u);
                        if (i) {
                            if (m > 0) for (; y--;) v[y] || h[y] || (h[y] = G.call(l));
                            h = g(h)
                        }
                        Q.apply(l, h), b && !i && h.length > 0 && m + t.length > 1 && a.uniqueSort(l)
                    }
                    return b && ($ = w, j = x), v
                };
            return r ? i(s) : s
        }

        function b(e, t, n) {
            for (var r = 0, i = t.length; i > r; r++) a(e, t[r], n);
            return n
        }

        function x(e, t, n, r) {
            var i, o, a, s, u, l = f(e);
            if (!r && 1 === l.length) {
                if (o = l[0] = l[0].slice(0), o.length > 2 && "ID" === (a = o[0]).type && 9 === t.nodeType && !q && C.relative[o[1].type]) {
                    if (t = C.find.ID(a.matches[0].replace(xt, Tt), t)[0], !t) return n;
                    e = e.slice(o.shift().value.length)
                }
                for (i = pt.needsContext.test(e) ? 0 : o.length; i-- && (a = o[i], !C.relative[s = a.type]);) if ((u = C.find[s]) && (r = u(a.matches[0].replace(xt, Tt), dt.test(o[0].type) && t.parentNode || t))) {
                    if (o.splice(i, 1), e = r.length && p(o), !e) return Q.apply(n, K.call(r, 0)), n;
                    break
                }
            }
            return S(e, l)(r, t, q, n, dt.test(e)), n
        }

        function T() {
        }

        var w, N, C, k, E, S, A, j, D, L, H, q, M, _, F, O, B, P = "sizzle" + -new Date,
            R = e.document,
            W = {},
            $ = 0,
            I = 0,
            z = r(),
            X = r(),
            U = r(),
            V = typeof t,
            Y = 1 << 31,
            J = [],
            G = J.pop,
            Q = J.push,
            K = J.slice,
            Z = J.indexOf ||
                function (e) {
                    for (var t = 0, n = this.length; n > t; t++) if (this[t] === e) return t;
                    return -1
                }, et = "[\ \\t\\r\\n\\f]", tt = "(?:\\\\.|[\\w-]|[^\-\ ])+", nt = tt.replace("w", "w#"), rt = "([*^$|!~]?=)", it = "\\[" + et + "*(" + tt + ")" + et + "*(?:" + rt + et + "*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + nt + ")|)|)" + et + "*\\]", ot = ":(" + tt + ")(?:\\(((['\"])((?:\\\\.|[^\\\\])*?)\\3|((?:\\\\.|[^\\\\()[\\]]|" + it.replace(3, 8) + ")*)|.*)\\)|)", at = RegExp("^" + et + "+|((?:^|[^\\\\])(?:\\\\.)*)" + et + "+$", "g"), st = RegExp("^" + et + "*," + et + "*"), lt = RegExp("^" + et + "*([\ \\t\\r\\n\\f>+~])" + et + "*"), ct = RegExp(ot), ft = RegExp("^" + nt + "$"), pt = {
                ID: RegExp("^#(" + tt + ")"),
                CLASS: RegExp("^\\.(" + tt + ")"),
                NAME: RegExp("^\\[name=['\"]?(" + tt + ")['\"]?\\]"),
                TAG: RegExp("^(" + tt.replace("w", "w*") + ")"),
                ATTR: RegExp("^" + it),
                PSEUDO: RegExp("^" + ot),
                CHILD: RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + et + "*(even|odd|(([+-]|)(\\d*)n|)" + et + "*(?:([+-]|)" + et + "*(\\d+)|))" + et + "*\\)|)", "i"),
                needsContext: RegExp("^" + et + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + et + "*((?:-\\d)?\\d*)" + et + "*\\)|)(?=[^-]|$)", "i")
            }, dt = /[ \t\r\n\f]*[+~]/, ht = /^[^{]+\{\s*\[native code/, gt = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/, mt = /^(?:input|select|textarea|button)$/i, yt = /^h\d$/i, vt = /'|\\/g, bt = /\=[ \t\r\n\f]*([^'"\]]*)[ \t\r\n\f]*\]/g, xt = /\\([\da-fA-F]{1,6}[ \t\r\n\f]?|.)/g, Tt = function (e, t) {
                var n = "0x" + t - 65536;
                return n !== n ? t : 0 > n ? String.fromCharCode(n + 65536) : String.fromCharCode(55296 | n >> 10, 56320 | 1023 & n)
            };
        try {
            K.call(R.documentElement.childNodes, 0)[0].nodeType
        } catch (wt) {
            K = function (e) {
                for (var t, n = []; t = this[e++];) n.push(t);
                return n
            }
        }
        E = a.isXML = function (e) {
            var t = e && (e.ownerDocument || e).documentElement;
            return t ? "HTML" !== t.nodeName : !1
        }, D = a.setDocument = function (e) {
            var r = e ? e.ownerDocument || e : R;
            return r !== L && 9 === r.nodeType && r.documentElement ? (L = r, H = r.documentElement, q = E(r), W.tagNameNoComments = o(function (e) {
                return e.appendChild(r.createComment("")), !e.getElementsByTagName("*").length
            }), W.attributes = o(function (e) {
                e.innerHTML = "<select></select>";
                var t = typeof e.lastChild.getAttribute("multiple");
                return "boolean" !== t && "string" !== t
            }), W.getByClassName = o(function (e) {
                return e.innerHTML = "<div class='hidden e'></div><div class='hidden'></div>", e.getElementsByClassName && e.getElementsByClassName("e").length ? (e.lastChild.className = "e", 2 === e.getElementsByClassName("e").length) : !1
            }), W.getByName = o(function (e) {
                e.id = P + 0, e.innerHTML = "<a name='" + P + "'></a><div name='" + P + "'></div>", H.insertBefore(e, H.firstChild);
                var t = r.getElementsByName && r.getElementsByName(P).length === 2 + r.getElementsByName(P + 0).length;
                return W.getIdNotName = !r.getElementById(P), H.removeChild(e), t
            }), C.attrHandle = o(function (e) {
                return e.innerHTML = "<a href='#'></a>", e.firstChild && typeof e.firstChild.getAttribute !== V && "#" === e.firstChild.getAttribute("href")
            }) ? {} : {
                href: function (e) {
                    return e.getAttribute("href", 2)
                },
                type: function (e) {
                    return e.getAttribute("type")
                }
            }, W.getIdNotName ? (C.find.ID = function (e, t) {
                if (typeof t.getElementById !== V && !q) {
                    var n = t.getElementById(e);
                    return n && n.parentNode ? [n] : []
                }
            }, C.filter.ID = function (e) {
                var t = e.replace(xt, Tt);
                return function (e) {
                    return e.getAttribute("id") === t
                }
            }) : (C.find.ID = function (e, n) {
                if (typeof n.getElementById !== V && !q) {
                    var r = n.getElementById(e);
                    return r ? r.id === e || typeof r.getAttributeNode !== V && r.getAttributeNode("id").value === e ? [r] : t : []
                }
            }, C.filter.ID = function (e) {
                var t = e.replace(xt, Tt);
                return function (e) {
                    var n = typeof e.getAttributeNode !== V && e.getAttributeNode("id");
                    return n && n.value === t
                }
            }), C.find.TAG = W.tagNameNoComments ?
                function (e, n) {
                    return typeof n.getElementsByTagName !== V ? n.getElementsByTagName(e) : t
                } : function (e, t) {
                var n, r = [],
                    i = 0,
                    o = t.getElementsByTagName(e);
                if ("*" === e) {
                    for (; n = o[i++];) 1 === n.nodeType && r.push(n);
                    return r
                }
                return o
            }, C.find.NAME = W.getByName &&
                function (e, n) {
                    return typeof n.getElementsByName !== V ? n.getElementsByName(name) : t
                }, C.find.CLASS = W.getByClassName &&
                function (e, n) {
                    return typeof n.getElementsByClassName === V || q ? t : n.getElementsByClassName(e)
                }, _ = [], M = [":focus"], (W.qsa = n(r.querySelectorAll)) && (o(function (e) {
                e.innerHTML = "<select><option selected=''></option></select>", e.querySelectorAll("[selected]").length || M.push("\\[" + et + "*(?:checked|disabled|ismap|multiple|readonly|selected|value)"), e.querySelectorAll(":checked").length || M.push(":checked")
            }), o(function (e) {
                e.innerHTML = "<input type='hidden' i=''/>", e.querySelectorAll("[i^='']").length && M.push("[*^$]=" + et + "*(?:\"\"|'')"), e.querySelectorAll(":enabled").length || M.push(":enabled", ":disabled"), e.querySelectorAll("*,:x"), M.push(",.*:")
            })), (W.matchesSelector = n(F = H.matchesSelector || H.mozMatchesSelector || H.webkitMatchesSelector || H.oMatchesSelector || H.msMatchesSelector)) && o(function (e) {
                W.disconnectedMatch = F.call(e, "div"), F.call(e, "[s!='']:x"), _.push("!=", ot)
            }), M = RegExp(M.join("|")), _ = RegExp(_.join("|")), O = n(H.contains) || H.compareDocumentPosition ?
                function (e, t) {
                    var n = 9 === e.nodeType ? e.documentElement : e,
                        r = t && t.parentNode;
                    return e === r || !(!r || 1 !== r.nodeType || !(n.contains ? n.contains(r) : e.compareDocumentPosition && 16 & e.compareDocumentPosition(r)))
                } : function (e, t) {
                if (t) for (; t = t.parentNode;) if (t === e) return !0;
                return !1
            }, B = H.compareDocumentPosition ?
                function (e, t) {
                    var n;
                    return e === t ? (A = !0, 0) : (n = t.compareDocumentPosition && e.compareDocumentPosition && e.compareDocumentPosition(t)) ? 1 & n || e.parentNode && 11 === e.parentNode.nodeType ? e === r || O(R, e) ? -1 : t === r || O(R, t) ? 1 : 0 : 4 & n ? -1 : 1 : e.compareDocumentPosition ? -1 : 1
                } : function (e, t) {
                var n, i = 0,
                    o = e.parentNode,
                    a = t.parentNode,
                    u = [e],
                    l = [t];
                if (e === t) return A = !0, 0;
                if (!o || !a) return e === r ? -1 : t === r ? 1 : o ? -1 : a ? 1 : 0;
                if (o === a) return s(e, t);
                for (n = e; n = n.parentNode;) u.unshift(n);
                for (n = t; n = n.parentNode;) l.unshift(n);
                for (; u[i] === l[i];) i++;
                return i ? s(u[i], l[i]) : u[i] === R ? -1 : l[i] === R ? 1 : 0
            }, A = !1, [0, 0].sort(B), W.detectDuplicates = A, L) : L
        }, a.matches = function (e, t) {
            return a(e, null, null, t)
        }, a.matchesSelector = function (e, t) {
            if ((e.ownerDocument || e) !== L && D(e), t = t.replace(bt, "='$1']"), !(!W.matchesSelector || q || _ && _.test(t) || M.test(t))) try {
                var n = F.call(e, t);
                if (n || W.disconnectedMatch || e.document && 11 !== e.document.nodeType) return n
            } catch (r) {
            }
            return a(t, L, null, [e]).length > 0
        }, a.contains = function (e, t) {
            return (e.ownerDocument || e) !== L && D(e), O(e, t)
        }, a.attr = function (e, t) {
            var n;
            return (e.ownerDocument || e) !== L && D(e), q || (t = t.toLowerCase()), (n = C.attrHandle[t]) ? n(e) : q || W.attributes ? e.getAttribute(t) : ((n = e.getAttributeNode(t)) || e.getAttribute(t)) && e[t] === !0 ? t : n && n.specified ? n.value : null
        }, a.error = function (e) {
            throw Error("Syntax error, unrecognized expression: " + e)
        }, a.uniqueSort = function (e) {
            var t, n = [],
                r = 1,
                i = 0;
            if (A = !W.detectDuplicates, e.sort(B), A) {
                for (; t = e[r]; r++) t === e[r - 1] && (i = n.push(r));
                for (; i--;) e.splice(n[i], 1)
            }
            return e
        }, k = a.getText = function (e) {
            var t, n = "",
                r = 0,
                i = e.nodeType;
            if (i) {
                if (1 === i || 9 === i || 11 === i) {
                    if ("string" == typeof e.textContent) return e.textContent;
                    for (e = e.firstChild; e; e = e.nextSibling) n += k(e)
                } else if (3 === i || 4 === i) return e.nodeValue
            } else for (; t = e[r]; r++) n += k(t);
            return n
        }, C = a.selectors = {
            cacheLength: 50,
            createPseudo: i,
            match: pt,
            find: {},
            relative: {
                ">": {
                    dir: "parentNode",
                    first: !0
                },
                " ": {
                    dir: "parentNode"
                },
                "+": {
                    dir: "previousSibling",
                    first: !0
                },
                "~": {
                    dir: "previousSibling"
                }
            },
            preFilter: {
                ATTR: function (e) {
                    return e[1] = e[1].replace(xt, Tt), e[3] = (e[4] || e[5] || "").replace(xt, Tt), "~=" === e[2] && (e[3] = " " + e[3] + " "), e.slice(0, 4)
                },
                CHILD: function (e) {
                    return e[1] = e[1].toLowerCase(), "nth" === e[1].slice(0, 3) ? (e[3] || a.error(e[0]), e[4] = +(e[4] ? e[5] + (e[6] || 1) : 2 * ("even" === e[3] || "odd" === e[3])), e[5] = +(e[7] + e[8] || "odd" === e[3])) : e[3] && a.error(e[0]), e
                },
                PSEUDO: function (e) {
                    var t, n = !e[5] && e[2];
                    return pt.CHILD.test(e[0]) ? null : (e[4] ? e[2] = e[4] : n && ct.test(n) && (t = f(n, !0)) && (t = n.indexOf(")", n.length - t) - n.length) && (e[0] = e[0].slice(0, t), e[2] = n.slice(0, t)), e.slice(0, 3))
                }
            },
            filter: {
                TAG: function (e) {
                    return "*" === e ?
                        function () {
                            return !0
                        } : (e = e.replace(xt, Tt).toLowerCase(), function (t) {
                        return t.nodeName && t.nodeName.toLowerCase() === e
                    })
                },
                CLASS: function (e) {
                    var t = z[e + " "];
                    return t || (t = RegExp("(^|" + et + ")" + e + "(" + et + "|$)")) && z(e, function (e) {
                            return t.test(e.className || typeof e.getAttribute !== V && e.getAttribute("class") || "")
                        })
                },
                ATTR: function (e, t, n) {
                    return function (r) {
                        var i = a.attr(r, e);
                        return null == i ? "!=" === t : t ? (i += "", "=" === t ? i === n : "!=" === t ? i !== n : "^=" === t ? n && 0 === i.indexOf(n) : "*=" === t ? n && i.indexOf(n) > -1 : "$=" === t ? n && i.slice(-n.length) === n : "~=" === t ? (" " + i + " ").indexOf(n) > -1 : "|=" === t ? i === n || i.slice(0, n.length + 1) === n + "-" : !1) : !0
                    }
                },
                CHILD: function (e, t, n, r, i) {
                    var o = "nth" !== e.slice(0, 3),
                        a = "last" !== e.slice(-4),
                        s = "of-type" === t;
                    return 1 === r && 0 === i ?
                        function (e) {
                            return !!e.parentNode
                        } : function (t, n, u) {
                        var l, c, f, p, d, h, g = o !== a ? "nextSibling" : "previousSibling",
                            m = t.parentNode,
                            y = s && t.nodeName.toLowerCase(),
                            v = !u && !s;
                        if (m) {
                            if (o) {
                                for (; g;) {
                                    for (f = t; f = f[g];) if (s ? f.nodeName.toLowerCase() === y : 1 === f.nodeType) return !1;
                                    h = g = "only" === e && !h && "nextSibling"
                                }
                                return !0
                            }
                            if (h = [a ? m.firstChild : m.lastChild], a && v) {
                                for (c = m[P] || (m[P] = {}), l = c[e] || [], d = l[0] === $ && l[1], p = l[0] === $ && l[2], f = d && m.childNodes[d]; f = ++d && f && f[g] || (p = d = 0) || h.pop();) if (1 === f.nodeType && ++p && f === t) {
                                    c[e] = [$, d, p];
                                    break
                                }
                            } else if (v && (l = (t[P] || (t[P] = {}))[e]) && l[0] === $) p = l[1];
                            else for (;
                                    (f = ++d && f && f[g] || (p = d = 0) || h.pop()) && ((s ? f.nodeName.toLowerCase() !== y : 1 !== f.nodeType) || !++p || (v && ((f[P] || (f[P] = {}))[e] = [$, p]), f !== t)););
                            return p -= i, p === r || 0 === p % r && p / r >= 0
                        }
                    }
                },
                PSEUDO: function (e, t) {
                    var n, r = C.pseudos[e] || C.setFilters[e.toLowerCase()] || a.error("unsupported pseudo: " + e);
                    return r[P] ? r(t) : r.length > 1 ? (n = [e, e, "", t], C.setFilters.hasOwnProperty(e.toLowerCase()) ? i(function (e, n) {
                        for (var i, o = r(e, t), a = o.length; a--;) i = Z.call(e, o[a]), e[i] = !(n[i] = o[a])
                    }) : function (e) {
                        return r(e, 0, n)
                    }) : r
                }
            },
            pseudos: {
                not: i(function (e) {
                    var t = [],
                        n = [],
                        r = S(e.replace(at, "$1"));
                    return r[P] ? i(function (e, t, n, i) {
                        for (var o, a = r(e, null, i, []), s = e.length; s--;)(o = a[s]) && (e[s] = !(t[s] = o))
                    }) : function (e, i, o) {
                        return t[0] = e, r(t, null, o, n), !n.pop()
                    }
                }),
                has: i(function (e) {
                    return function (t) {
                        return a(e, t).length > 0
                    }
                }),
                contains: i(function (e) {
                    return function (t) {
                        return (t.textContent || t.innerText || k(t)).indexOf(e) > -1
                    }
                }),
                lang: i(function (e) {
                    return ft.test(e || "") || a.error("unsupported lang: " + e), e = e.replace(xt, Tt).toLowerCase(), function (t) {
                        var n;
                        do
                            if (n = q ? t.getAttribute("xml:lang") || t.getAttribute("lang") : t.lang) return n = n.toLowerCase(), n === e || 0 === n.indexOf(e + "-");
                        while ((t = t.parentNode) && 1 === t.nodeType);
                        return !1
                    }
                }),
                target: function (t) {
                    var n = e.location && e.location.hash;
                    return n && n.slice(1) === t.id
                },
                root: function (e) {
                    return e === H
                },
                focus: function (e) {
                    return e === L.activeElement && (!L.hasFocus || L.hasFocus()) && !!(e.type || e.href || ~e.tabIndex)
                },
                enabled: function (e) {
                    return e.disabled === !1
                },
                disabled: function (e) {
                    return e.disabled === !0
                },
                checked: function (e) {
                    var t = e.nodeName.toLowerCase();
                    return "input" === t && !!e.checked || "option" === t && !!e.selected
                },
                selected: function (e) {
                    return e.parentNode && e.parentNode.selectedIndex, e.selected === !0
                },
                empty: function (e) {
                    for (e = e.firstChild; e; e = e.nextSibling) if (e.nodeName > "@" || 3 === e.nodeType || 4 === e.nodeType) return !1;
                    return !0
                },
                parent: function (e) {
                    return !C.pseudos.empty(e)
                },
                header: function (e) {
                    return yt.test(e.nodeName)
                },
                input: function (e) {
                    return mt.test(e.nodeName)
                },
                button: function (e) {
                    var t = e.nodeName.toLowerCase();
                    return "input" === t && "button" === e.type || "button" === t
                },
                text: function (e) {
                    var t;
                    return "input" === e.nodeName.toLowerCase() && "text" === e.type && (null == (t = e.getAttribute("type")) || t.toLowerCase() === e.type)
                },
                first: c(function () {
                    return [0]
                }),
                last: c(function (e, t) {
                    return [t - 1]
                }),
                eq: c(function (e, t, n) {
                    return [0 > n ? n + t : n]
                }),
                even: c(function (e, t) {
                    for (var n = 0; t > n; n += 2) e.push(n);
                    return e
                }),
                odd: c(function (e, t) {
                    for (var n = 1; t > n; n += 2) e.push(n);
                    return e
                }),
                lt: c(function (e, t, n) {
                    for (var r = 0 > n ? n + t : n; --r >= 0;) e.push(r);
                    return e
                }),
                gt: c(function (e, t, n) {
                    for (var r = 0 > n ? n + t : n; t > ++r;) e.push(r);
                    return e
                })
            }
        };
        for (w in {
            radio: !0,
            checkbox: !0,
            file: !0,
            password: !0,
            image: !0
        }) C.pseudos[w] = u(w);
        for (w in {
            submit: !0,
            reset: !0
        }) C.pseudos[w] = l(w);
        S = a.compile = function (e, t) {
            var n, r = [],
                i = [],
                o = U[e + " "];
            if (!o) {
                for (t || (t = f(e)), n = t.length; n--;) o = y(t[n]), o[P] ? r.push(o) : i.push(o);
                o = U(e, v(i, r))
            }
            return o
        }, C.pseudos.nth = C.pseudos.eq, C.filters = T.prototype = C.pseudos, C.setFilters = new T, D(), a.attr = ut.attr, ut.find = a, ut.expr = a.selectors, ut.expr[":"] = ut.expr.pseudos, ut.unique = a.uniqueSort, ut.text = a.getText, ut.isXMLDoc = a.isXML, ut.contains = a.contains
    }(e);
    var Wt = /Until$/,
        $t = /^(?:parents|prev(?:Until|All))/,
        It = /^.[^:#\[\.,]*$/,
        zt = ut.expr.match.needsContext,
        Xt = {
            children: !0,
            contents: !0,
            next: !0,
            prev: !0
        };
    ut.fn.extend({
        find: function (e) {
            var t, n, r, i = this.length;
            if ("string" != typeof e) return r = this, this.pushStack(ut(e).filter(function () {
                for (t = 0; i > t; t++) if (ut.contains(r[t], this)) return !0
            }));
            for (n = [], t = 0; i > t; t++) ut.find(e, this[t], n);
            return n = this.pushStack(i > 1 ? ut.unique(n) : n), n.selector = (this.selector ? this.selector + " " : "") + e, n
        },
        has: function (e) {
            var t, n = ut(e, this),
                r = n.length;
            return this.filter(function () {
                for (t = 0; r > t; t++) if (ut.contains(this, n[t])) return !0
            })
        },
        not: function (e) {
            return this.pushStack(f(this, e, !1))
        },
        filter: function (e) {
            return this.pushStack(f(this, e, !0))
        },
        is: function (e) {
            return !!e && ("string" == typeof e ? zt.test(e) ? ut(e, this.context).index(this[0]) >= 0 : ut.filter(e, this).length > 0 : this.filter(e).length > 0)
        },
        closest: function (e, t) {
            for (var n, r = 0, i = this.length, o = [], a = zt.test(e) || "string" != typeof e ? ut(e, t || this.context) : 0; i > r; r++) for (n = this[r]; n && n.ownerDocument && n !== t && 11 !== n.nodeType;) {
                if (a ? a.index(n) > -1 : ut.find.matchesSelector(n, e)) {
                    o.push(n);
                    break
                }
                n = n.parentNode
            }
            return this.pushStack(o.length > 1 ? ut.unique(o) : o)
        },
        index: function (e) {
            return e ? "string" == typeof e ? ut.inArray(this[0], ut(e)) : ut.inArray(e.jquery ? e[0] : e, this) : this[0] && this[0].parentNode ? this.first().prevAll().length : -1
        },
        add: function (e, t) {
            var n = "string" == typeof e ? ut(e, t) : ut.makeArray(e && e.nodeType ? [e] : e),
                r = ut.merge(this.get(), n);
            return this.pushStack(ut.unique(r))
        },
        addBack: function (e) {
            return this.add(null == e ? this.prevObject : this.prevObject.filter(e))
        }
    }), ut.fn.andSelf = ut.fn.addBack, ut.each({
        parent: function (e) {
            var t = e.parentNode;
            return t && 11 !== t.nodeType ? t : null
        },
        parents: function (e) {
            return ut.dir(e, "parentNode")
        },
        parentsUntil: function (e, t, n) {
            return ut.dir(e, "parentNode", n)
        },
        next: function (e) {
            return c(e, "nextSibling")
        },
        prev: function (e) {
            return c(e, "previousSibling")
        },
        nextAll: function (e) {
            return ut.dir(e, "nextSibling")
        },
        prevAll: function (e) {
            return ut.dir(e, "previousSibling")
        },
        nextUntil: function (e, t, n) {
            return ut.dir(e, "nextSibling", n)
        },
        prevUntil: function (e, t, n) {
            return ut.dir(e, "previousSibling", n)
        },
        siblings: function (e) {
            return ut.sibling((e.parentNode || {}).firstChild, e)
        },
        children: function (e) {
            return ut.sibling(e.firstChild)
        },
        contents: function (e) {
            return ut.nodeName(e, "iframe") ? e.contentDocument || e.contentWindow.document : ut.merge([], e.childNodes)
        }
    }, function (e, t) {
        ut.fn[e] = function (n, r) {
            var i = ut.map(this, t, n);
            return Wt.test(e) || (r = n), r && "string" == typeof r && (i = ut.filter(r, i)), i = this.length > 1 && !Xt[e] ? ut.unique(i) : i, this.length > 1 && $t.test(e) && (i = i.reverse()), this.pushStack(i)
        }
    }), ut.extend({
        filter: function (e, t, n) {
            return n && (e = ":not(" + e + ")"), 1 === t.length ? ut.find.matchesSelector(t[0], e) ? [t[0]] : [] : ut.find.matches(e, t)
        },
        dir: function (e, n, r) {
            for (var i = [], o = e[n]; o && 9 !== o.nodeType && (r === t || 1 !== o.nodeType || !ut(o).is(r));) 1 === o.nodeType && i.push(o), o = o[n];
            return i
        },
        sibling: function (e, t) {
            for (var n = []; e; e = e.nextSibling) 1 === e.nodeType && e !== t && n.push(e);
            return n
        }
    });
    var Ut = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video",
        Vt = / jQuery\d+="(?:null|\d+)"/g,
        Yt = RegExp("<(?:" + Ut + ")[\\s/>]", "i"),
        Jt = /^\s+/,
        Gt = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,
        Qt = /<([\w:]+)/,
        Kt = /<tbody/i,
        Zt = /<|&#?\w+;/,
        en = /<(?:script|style|link)/i,
        tn = /^(?:checkbox|radio)$/i,
        nn = /checked\s*(?:[^=]|=\s*.checked.)/i,
        rn = /^$|\/(?:java|ecma)script/i,
        on = /^true\/(.*)/,
        an = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g,
        sn = {
            option: [1, "<select multiple='multiple'>", "</select>"],
            legend: [1, "<fieldset>", "</fieldset>"],
            area: [1, "<map>", "</map>"],
            param: [1, "<object>", "</object>"],
            thead: [1, "<table>", "</table>"],
            tr: [2, "<table><tbody>", "</tbody></table>"],
            col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
            td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
            _default: ut.support.htmlSerialize ? [0, "", ""] : [1, "X<div>", "</div>"]
        },
        un = p(Y),
        ln = un.appendChild(Y.createElement("div"));
    sn.optgroup = sn.option, sn.tbody = sn.tfoot = sn.colgroup = sn.caption = sn.thead, sn.th = sn.td, ut.fn.extend({
        text: function (e) {
            return ut.access(this, function (e) {
                return e === t ? ut.text(this) : this.empty().append((this[0] && this[0].ownerDocument || Y).createTextNode(e))
            }, null, e, arguments.length)
        },
        wrapAll: function (e) {
            if (ut.isFunction(e)) return this.each(function (t) {
                ut(this).wrapAll(e.call(this, t))
            });
            if (this[0]) {
                var t = ut(e, this[0].ownerDocument).eq(0).clone(!0);
                this[0].parentNode && t.insertBefore(this[0]), t.map(function () {
                    for (var e = this; e.firstChild && 1 === e.firstChild.nodeType;) e = e.firstChild;
                    return e
                }).append(this)
            }
            return this
        },
        wrapInner: function (e) {
            return this.each(ut.isFunction(e) ?
                function (t) {
                    ut(this).wrapInner(e.call(this, t))
                } : function () {
                var t = ut(this),
                    n = t.contents();
                n.length ? n.wrapAll(e) : t.append(e)
            })
        },
        wrap: function (e) {
            var t = ut.isFunction(e);
            return this.each(function (n) {
                ut(this).wrapAll(t ? e.call(this, n) : e)
            })
        },
        unwrap: function () {
            return this.parent().each(function () {
                ut.nodeName(this, "body") || ut(this).replaceWith(this.childNodes)
            }).end()
        },
        append: function () {
            return this.domManip(arguments, !0, function (e) {
                (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) && this.appendChild(e)
            })
        },
        prepend: function () {
            return this.domManip(arguments, !0, function (e) {
                (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) && this.insertBefore(e, this.firstChild)
            })
        },
        before: function () {
            return this.domManip(arguments, !1, function (e) {
                this.parentNode && this.parentNode.insertBefore(e, this)
            })
        },
        after: function () {
            return this.domManip(arguments, !1, function (e) {
                this.parentNode && this.parentNode.insertBefore(e, this.nextSibling)
            })
        },
        remove: function (e, t) {
            for (var n, r = 0; null != (n = this[r]); r++)(!e || ut.filter(e, [n]).length > 0) && (t || 1 !== n.nodeType || ut.cleanData(b(n)), n.parentNode && (t && ut.contains(n.ownerDocument, n) && m(b(n, "script")), n.parentNode.removeChild(n)));
            return this
        },
        empty: function () {
            for (var e, t = 0; null != (e = this[t]); t++) {
                for (1 === e.nodeType && ut.cleanData(b(e, !1)); e.firstChild;) e.removeChild(e.firstChild);
                e.options && ut.nodeName(e, "select") && (e.options.length = 0)
            }
            return this
        },
        clone: function (e, t) {
            return e = null == e ? !1 : e, t = null == t ? e : t, this.map(function () {
                return ut.clone(this, e, t)
            })
        },
        html: function (e) {
            return ut.access(this, function (e) {
                var n = this[0] || {},
                    r = 0,
                    i = this.length;
                if (e === t) return 1 === n.nodeType ? n.innerHTML.replace(Vt, "") : t;
                if (!("string" != typeof e || en.test(e) || !ut.support.htmlSerialize && Yt.test(e) || !ut.support.leadingWhitespace && Jt.test(e) || sn[(Qt.exec(e) || ["", ""])[1].toLowerCase()])) {
                    e = e.replace(Gt, "<$1></$2>");
                    try {
                        for (; i > r; r++) n = this[r] || {}, 1 === n.nodeType && (ut.cleanData(b(n, !1)), n.innerHTML = e);
                        n = 0
                    } catch (o) {
                    }
                }
                n && this.empty().append(e)
            }, null, e, arguments.length)
        },
        replaceWith: function (e) {
            var t = ut.isFunction(e);
            return t || "string" == typeof e || (e = ut(e).not(this).detach()), this.domManip([e], !0, function (e) {
                var t = this.nextSibling,
                    n = this.parentNode;
                n && (ut(this).remove(), n.insertBefore(e, t))
            })
        },
        detach: function (e) {
            return this.remove(e, !0)
        },
        domManip: function (e, n, r) {
            e = tt.apply([], e);
            var i, o, a, s, u, l, c = 0,
                f = this.length,
                p = this,
                m = f - 1,
                y = e[0],
                v = ut.isFunction(y);
            if (v || !(1 >= f || "string" != typeof y || ut.support.checkClone) && nn.test(y)) return this.each(function (i) {
                var o = p.eq(i);
                v && (e[0] = y.call(this, i, n ? o.html() : t)), o.domManip(e, n, r)
            });
            if (f && (l = ut.buildFragment(e, this[0].ownerDocument, !1, this), i = l.firstChild, 1 === l.childNodes.length && (l = i), i)) {
                for (n = n && ut.nodeName(i, "tr"), s = ut.map(b(l, "script"), h), a = s.length; f > c; c++) o = l, c !== m && (o = ut.clone(o, !0, !0), a && ut.merge(s, b(o, "script"))), r.call(n && ut.nodeName(this[c], "table") ? d(this[c], "tbody") : this[c], o, c);
                if (a) for (u = s[s.length - 1].ownerDocument, ut.map(s, g), c = 0; a > c; c++) o = s[c], rn.test(o.type || "") && !ut._data(o, "globalEval") && ut.contains(u, o) && (o.src ? ut.ajax({
                    url: o.src,
                    type: "GET",
                    dataType: "script",
                    async: !1,
                    global: !1,
                    "throws": !0
                }) : ut.globalEval((o.text || o.textContent || o.innerHTML || "").replace(an, "")));
                l = i = null
            }
            return this
        }
    }), ut.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    }, function (e, t) {
        ut.fn[e] = function (e) {
            for (var n, r = 0, i = [], o = ut(e), a = o.length - 1; a >= r; r++) n = r === a ? this : this.clone(!0), ut(o[r])[t](n), nt.apply(i, n.get());
            return this.pushStack(i)
        }
    }), ut.extend({
        clone: function (e, t, n) {
            var r, i, o, a, s, u = ut.contains(e.ownerDocument, e);
            if (ut.support.html5Clone || ut.isXMLDoc(e) || !Yt.test("<" + e.nodeName + ">") ? o = e.cloneNode(!0) : (ln.innerHTML = e.outerHTML, ln.removeChild(o = ln.firstChild)), !(ut.support.noCloneEvent && ut.support.noCloneChecked || 1 !== e.nodeType && 11 !== e.nodeType || ut.isXMLDoc(e))) for (r = b(o), s = b(e), a = 0; null != (i = s[a]); ++a) r[a] && v(i, r[a]);
            if (t) if (n) for (s = s || b(e), r = r || b(o), a = 0; null != (i = s[a]); a++) y(i, r[a]);
            else y(e, o);
            return r = b(o, "script"), r.length > 0 && m(r, !u && b(e, "script")), r = s = i = null, o
        },
        buildFragment: function (e, t, n, r) {
            for (var i, o, a, s, u, l, c, f = e.length, d = p(t), h = [], g = 0; f > g; g++) if (o = e[g], o || 0 === o) if ("object" === ut.type(o)) ut.merge(h, o.nodeType ? [o] : o);
            else if (Zt.test(o)) {
                for (s = s || d.appendChild(t.createElement("div")), u = (Qt.exec(o) || ["", ""])[1].toLowerCase(), c = sn[u] || sn._default, s.innerHTML = c[1] + o.replace(Gt, "<$1></$2>") + c[2], i = c[0]; i--;) s = s.lastChild;
                if (!ut.support.leadingWhitespace && Jt.test(o) && h.push(t.createTextNode(Jt.exec(o)[0])), !ut.support.tbody) for (o = "table" !== u || Kt.test(o) ? "<table>" !== c[1] || Kt.test(o) ? 0 : s : s.firstChild, i = o && o.childNodes.length; i--;) ut.nodeName(l = o.childNodes[i], "tbody") && !l.childNodes.length && o.removeChild(l);
                for (ut.merge(h, s.childNodes), s.textContent = ""; s.firstChild;) s.removeChild(s.firstChild);
                s = d.lastChild
            } else h.push(t.createTextNode(o));
            for (s && d.removeChild(s), ut.support.appendChecked || ut.grep(b(h, "input"), x), g = 0; o = h[g++];) if ((!r || -1 === ut.inArray(o, r)) && (a = ut.contains(o.ownerDocument, o), s = b(d.appendChild(o), "script"), a && m(s), n)) for (i = 0; o = s[i++];) rn.test(o.type || "") && n.push(o);
            return s = null, d
        },
        cleanData: function (e, t) {
            for (var n, r, i, o, a = 0, s = ut.expando, u = ut.cache, l = ut.support.deleteExpando, c = ut.event.special; null != (n = e[a]); a++) if ((t || ut.acceptData(n)) && (i = n[s], o = i && u[i])) {
                if (o.events) for (r in o.events) c[r] ? ut.event.remove(n, r) : ut.removeEvent(n, r, o.handle);
                u[i] && (delete u[i], l ? delete n[s] : typeof n.removeAttribute !== V ? n.removeAttribute(s) : n[s] = null, Z.push(i))
            }
        }
    });
    var cn, fn, pn, dn = /alpha\([^)]*\)/i,
        hn = /opacity\s*=\s*([^)]*)/,
        gn = /^(top|right|bottom|left)$/,
        mn = /^(none|table(?!-c[ea]).+)/,
        yn = /^margin/,
        vn = RegExp("^(" + lt + ")(.*)$", "i"),
        bn = RegExp("^(" + lt + ")(?!px)[a-z%]+$", "i"),
        xn = RegExp("^([+-])=(" + lt + ")", "i"),
        Tn = {
            BODY: "block"
        },
        wn = {
            position: "absolute",
            visibility: "hidden",
            display: "block"
        },
        Nn = {
            letterSpacing: 0,
            fontWeight: 400
        },
        Cn = ["Top", "Right", "Bottom", "Left"],
        kn = ["Webkit", "O", "Moz", "ms"];
    ut.fn.extend({
        css: function (e, n) {
            return ut.access(this, function (e, n, r) {
                var i, o, a = {},
                    s = 0;
                if (ut.isArray(n)) {
                    for (o = fn(e), i = n.length; i > s; s++) a[n[s]] = ut.css(e, n[s], !1, o);
                    return a
                }
                return r !== t ? ut.style(e, n, r) : ut.css(e, n)
            }, e, n, arguments.length > 1)
        },
        show: function () {
            return N(this, !0)
        },
        hide: function () {
            return N(this)
        },
        toggle: function (e) {
            var t = "boolean" == typeof e;
            return this.each(function () {
                (t ? e : w(this)) ? ut(this).show() : ut(this).hide()
            })
        }
    }), ut.extend({
        cssHooks: {
            opacity: {
                get: function (e, t) {
                    if (t) {
                        var n = pn(e, "opacity");
                        return "" === n ? "1" : n
                    }
                }
            }
        },
        cssNumber: {
            columnCount: !0,
            fillOpacity: !0,
            fontWeight: !0,
            lineHeight: !0,
            opacity: !0,
            orphans: !0,
            widows: !0,
            zIndex: !0,
            zoom: !0
        },
        cssProps: {
            "float": ut.support.cssFloat ? "cssFloat" : "styleFloat"
        },
        style: function (e, n, r, i) {
            if (e && 3 !== e.nodeType && 8 !== e.nodeType && e.style) {
                var o, a, s, u = ut.camelCase(n),
                    l = e.style;
                if (n = ut.cssProps[u] || (ut.cssProps[u] = T(l, u)), s = ut.cssHooks[n] || ut.cssHooks[u], r === t) return s && "get" in s && (o = s.get(e, !1, i)) !== t ? o : l[n];
                if (a = typeof r, "string" === a && (o = xn.exec(r)) && (r = (o[1] + 1) * o[2] + parseFloat(ut.css(e, n)), a = "number"), !(null == r || "number" === a && isNaN(r) || ("number" !== a || ut.cssNumber[u] || (r += "px"), ut.support.clearCloneStyle || "" !== r || 0 !== n.indexOf("background") || (l[n] = "inherit"), s && "set" in s && (r = s.set(e, r, i)) === t))) try {
                    l[n] = r
                } catch (c) {
                }
            }
        },
        css: function (e, n, r, i) {
            var o, a, s, u = ut.camelCase(n);
            return n = ut.cssProps[u] || (ut.cssProps[u] = T(e.style, u)), s = ut.cssHooks[n] || ut.cssHooks[u], s && "get" in s && (a = s.get(e, !0, r)), a === t && (a = pn(e, n, i)), "normal" === a && n in Nn && (a = Nn[n]), "" === r || r ? (o = parseFloat(a), r === !0 || ut.isNumeric(o) ? o || 0 : a) : a
        },
        swap: function (e, t, n, r) {
            var i, o, a = {};
            for (o in t) a[o] = e.style[o], e.style[o] = t[o];
            i = n.apply(e, r || []);
            for (o in t) e.style[o] = a[o];
            return i
        }
    }), e.getComputedStyle ? (fn = function (t) {
        return e.getComputedStyle(t, null)
    }, pn = function (e, n, r) {
        var i, o, a, s = r || fn(e),
            u = s ? s.getPropertyValue(n) || s[n] : t,
            l = e.style;
        return s && ("" !== u || ut.contains(e.ownerDocument, e) || (u = ut.style(e, n)), bn.test(u) && yn.test(n) && (i = l.width, o = l.minWidth, a = l.maxWidth, l.minWidth = l.maxWidth = l.width = u, u = s.width, l.width = i, l.minWidth = o, l.maxWidth = a)), u
    }) : Y.documentElement.currentStyle && (fn = function (e) {
        return e.currentStyle
    }, pn = function (e, n, r) {
        var i, o, a, s = r || fn(e),
            u = s ? s[n] : t,
            l = e.style;
        return null == u && l && l[n] && (u = l[n]), bn.test(u) && !gn.test(n) && (i = l.left, o = e.runtimeStyle, a = o && o.left, a && (o.left = e.currentStyle.left), l.left = "fontSize" === n ? "1em" : u, u = l.pixelLeft + "px", l.left = i, a && (o.left = a)), "" === u ? "auto" : u
    }), ut.each(["height", "width"], function (e, n) {
        ut.cssHooks[n] = {
            get: function (e, r, i) {
                return r ? 0 === e.offsetWidth && mn.test(ut.css(e, "display")) ? ut.swap(e, wn, function () {
                    return E(e, n, i)
                }) : E(e, n, i) : t
            },
            set: function (e, t, r) {
                var i = r && fn(e);
                return C(e, t, r ? k(e, n, r, ut.support.boxSizing && "border-box" === ut.css(e, "boxSizing", !1, i), i) : 0)
            }
        }
    }), ut.support.opacity || (ut.cssHooks.opacity = {
        get: function (e, t) {
            return hn.test((t && e.currentStyle ? e.currentStyle.filter : e.style.filter) || "") ? .01 * parseFloat(RegExp.$1) + "" : t ? "1" : ""
        },
        set: function (e, t) {
            var n = e.style,
                r = e.currentStyle,
                i = ut.isNumeric(t) ? "alpha(opacity=" + 100 * t + ")" : "",
                o = r && r.filter || n.filter || "";
            n.zoom = 1, (t >= 1 || "" === t) && "" === ut.trim(o.replace(dn, "")) && n.removeAttribute && (n.removeAttribute("filter"), "" === t || r && !r.filter) || (n.filter = dn.test(o) ? o.replace(dn, i) : o + " " + i)
        }
    }), ut(function () {
        ut.support.reliableMarginRight || (ut.cssHooks.marginRight = {
            get: function (e, n) {
                return n ? ut.swap(e, {
                    display: "inline-block"
                }, pn, [e, "marginRight"]) : t
            }
        }), !ut.support.pixelPosition && ut.fn.position && ut.each(["top", "left"], function (e, n) {
            ut.cssHooks[n] = {
                get: function (e, r) {
                    return r ? (r = pn(e, n), bn.test(r) ? ut(e).position()[n] + "px" : r) : t
                }
            }
        })
    }), ut.expr && ut.expr.filters && (ut.expr.filters.hidden = function (e) {
        return 0 >= e.offsetWidth && 0 >= e.offsetHeight || !ut.support.reliableHiddenOffsets && "none" === (e.style && e.style.display || ut.css(e, "display"))
    }, ut.expr.filters.visible = function (e) {
        return !ut.expr.filters.hidden(e)
    }), ut.each({
        margin: "",
        padding: "",
        border: "Width"
    }, function (e, t) {
        ut.cssHooks[e + t] = {
            expand: function (n) {
                for (var r = 0, i = {}, o = "string" == typeof n ? n.split(" ") : [n]; 4 > r; r++) i[e + Cn[r] + t] = o[r] || o[r - 2] || o[0];
                return i
            }
        }, yn.test(e) || (ut.cssHooks[e + t].set = C)
    });
    var En = /%20/g,
        Sn = /\[\]$/,
        An = /\r?\n/g,
        jn = /^(?:submit|button|image|reset|file)$/i,
        Dn = /^(?:input|select|textarea|keygen)/i;
    ut.fn.extend({
        serialize: function () {
            return ut.param(this.serializeArray())
        },
        serializeArray: function () {
            return this.map(function () {
                var e = ut.prop(this, "elements");
                return e ? ut.makeArray(e) : this
            }).filter(function () {
                var e = this.type;
                return this.name && !ut(this).is(":disabled") && Dn.test(this.nodeName) && !jn.test(e) && (this.checked || !tn.test(e))
            }).map(function (e, t) {
                var n = ut(this).val();
                return null == n ? null : ut.isArray(n) ? ut.map(n, function (e) {
                    return {
                        name: t.name,
                        value: e.replace(An, "\r\n")
                    }
                }) : {
                    name: t.name,
                    value: n.replace(An, "\r\n")
                }
            }).get()
        }
    }), ut.param = function (e, n) {
        var r, i = [],
            o = function (e, t) {
                t = ut.isFunction(t) ? t() : null == t ? "" : t, i[i.length] = encodeURIComponent(e) + "=" + encodeURIComponent(t)
            };
        if (n === t && (n = ut.ajaxSettings && ut.ajaxSettings.traditional), ut.isArray(e) || e.jquery && !ut.isPlainObject(e)) ut.each(e, function () {
            o(this.name, this.value)
        });
        else for (r in e) j(r, e[r], n, o);
        return i.join("&").replace(En, "+")
    }, ut.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "), function (e, t) {
        ut.fn[t] = function (e, n) {
            return arguments.length > 0 ? this.on(t, null, e, n) : this.trigger(t)
        }
    }), ut.fn.hover = function (e, t) {
        return this.mouseenter(e).mouseleave(t || e)
    };
    var Ln, Hn, qn = ut.now(),
        Mn = /\?/,
        _n = /#.*$/,
        Fn = /([?&])_=[^&]*/,
        On = /^(.*?):[ \t]*([^\r\n]*)\r?$/gm,
        Bn = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/,
        Pn = /^(?:GET|HEAD)$/,
        Rn = /^\/\//,
        Wn = /^([\w.+-]+:)(?:\/\/([^\/?#:]*)(?::(\d+)|)|)/,
        $n = ut.fn.load,
        In = {},
        zn = {},
        Xn = "*/".concat("*");
    try {
        Hn = J.href
    } catch (Un) {
        Hn = Y.createElement("a"), Hn.href = "", Hn = Hn.href
    }
    Ln = Wn.exec(Hn.toLowerCase()) || [], ut.fn.load = function (e, n, r) {
        if ("string" != typeof e && $n) return $n.apply(this, arguments);
        var i, o, a, s = this,
            u = e.indexOf(" ");
        return u >= 0 && (i = e.slice(u, e.length), e = e.slice(0, u)), ut.isFunction(n) ? (r = n, n = t) : n && "object" == typeof n && (a = "POST"), s.length > 0 && ut.ajax({
            url: e,
            type: a,
            dataType: "html",
            data: n
        }).done(function (e) {
            o = arguments, s.html(i ? ut("<div>").append(ut.parseHTML(e)).find(i) : e)
        }).complete(r &&
            function (e, t) {
                s.each(r, o || [e.responseText, t, e])
            }), this
    }, ut.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"], function (e, t) {
        ut.fn[t] = function (e) {
            return this.on(t, e)
        }
    }), ut.each(["get", "post"], function (e, n) {
        ut[n] = function (e, r, i, o) {
            return ut.isFunction(r) && (o = o || i, i = r, r = t), ut.ajax({
                url: e,
                type: n,
                dataType: o,
                data: r,
                success: i
            })
        }
    }), ut.extend({
        active: 0,
        lastModified: {},
        etag: {},
        ajaxSettings: {
            url: Hn,
            type: "GET",
            isLocal: Bn.test(Ln[1]),
            global: !0,
            processData: !0,
            async: !0,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            accepts: {
                "*": Xn,
                text: "text/plain",
                html: "text/html",
                xml: "application/xml, text/xml",
                json: "application/json, text/javascript"
            },
            contents: {
                xml: /xml/,
                html: /html/,
                json: /json/
            },
            responseFields: {
                xml: "responseXML",
                text: "responseText"
            },
            converters: {
                "* text": e.String,
                "text html": !0,
                "text json": ut.parseJSON,
                "text xml": ut.parseXML
            },
            flatOptions: {
                url: !0,
                context: !0
            }
        },
        ajaxSetup: function (e, t) {
            return t ? H(H(e, ut.ajaxSettings), t) : H(ut.ajaxSettings, e)
        },
        ajaxPrefilter: D(In),
        ajaxTransport: D(zn),
        ajax: function (e, n) {
            function r(e, n, r, i) {
                var o, f, v, b, T, N = n;
                2 !== x && (x = 2, u && clearTimeout(u), c = t, s = i || "", w.readyState = e > 0 ? 4 : 0, r && (b = q(p, w, r)), e >= 200 && 300 > e || 304 === e ? (p.ifModified && (T = w.getResponseHeader("Last-Modified"), T && (ut.lastModified[a] = T), T = w.getResponseHeader("etag"), T && (ut.etag[a] = T)), 204 === e ? (o = !0, N = "nocontent") : 304 === e ? (o = !0, N = "notmodified") : (o = M(p, b), N = o.state, f = o.data, v = o.error, o = !v)) : (v = N, (e || !N) && (N = "error", 0 > e && (e = 0))), w.status = e, w.statusText = (n || N) + "", o ? g.resolveWith(d, [f, N, w]) : g.rejectWith(d, [w, N, v]), w.statusCode(y), y = t, l && h.trigger(o ? "ajaxSuccess" : "ajaxError", [w, p, o ? f : v]), m.fireWith(d, [w, N]), l && (h.trigger("ajaxComplete", [w, p]), --ut.active || ut.event.trigger("ajaxStop")))
            }

            "object" == typeof e && (n = e, e = t), n = n || {};
            var i, o, a, s, u, l, c, f, p = ut.ajaxSetup({}, n),
                d = p.context || p,
                h = p.context && (d.nodeType || d.jquery) ? ut(d) : ut.event,
                g = ut.Deferred(),
                m = ut.Callbacks("once memory"),
                y = p.statusCode || {},
                v = {},
                b = {},
                x = 0,
                T = "canceled",
                w = {
                    readyState: 0,
                    getResponseHeader: function (e) {
                        var t;
                        if (2 === x) {
                            if (!f) for (f = {}; t = On.exec(s);) f[t[1].toLowerCase()] = t[2];
                            t = f[e.toLowerCase()]
                        }
                        return null == t ? null : t
                    },
                    getAllResponseHeaders: function () {
                        return 2 === x ? s : null
                    },
                    setRequestHeader: function (e, t) {
                        var n = e.toLowerCase();
                        return x || (e = b[n] = b[n] || e, v[e] = t), this
                    },
                    overrideMimeType: function (e) {
                        return x || (p.mimeType = e), this
                    },
                    statusCode: function (e) {
                        var t;
                        if (e) if (2 > x) for (t in e) y[t] = [y[t], e[t]];
                        else w.always(e[w.status]);
                        return this
                    },
                    abort: function (e) {
                        var t = e || T;
                        return c && c.abort(t), r(0, t), this
                    }
                };
            if (g.promise(w).complete = m.add, w.success = w.done, w.error = w.fail, p.url = ((e || p.url || Hn) + "").replace(_n, "").replace(Rn, Ln[1] + "//"), p.type = n.method || n.type || p.method || p.type, p.dataTypes = ut.trim(p.dataType || "*").toLowerCase().match(ct) || [""], null == p.crossDomain && (i = Wn.exec(p.url.toLowerCase()), p.crossDomain = !(!i || i[1] === Ln[1] && i[2] === Ln[2] && (i[3] || ("http:" === i[1] ? 80 : 443)) == (Ln[3] || ("http:" === Ln[1] ? 80 : 443)))), p.data && p.processData && "string" != typeof p.data && (p.data = ut.param(p.data, p.traditional)), L(In, p, n, w), 2 === x) return w;
            l = p.global, l && 0 === ut.active++ && ut.event.trigger("ajaxStart"), p.type = p.type.toUpperCase(), p.hasContent = !Pn.test(p.type), a = p.url, p.hasContent || (p.data && (a = p.url += (Mn.test(a) ? "&" : "?") + p.data, delete p.data), p.cache === !1 && (p.url = Fn.test(a) ? a.replace(Fn, "$1_=" + qn++) : a + (Mn.test(a) ? "&" : "?") + "_=" + qn++)), p.ifModified && (ut.lastModified[a] && w.setRequestHeader("If-Modified-Since", ut.lastModified[a]), ut.etag[a] && w.setRequestHeader("If-None-Match", ut.etag[a])), (p.data && p.hasContent && p.contentType !== !1 || n.contentType) && w.setRequestHeader("Content-Type", p.contentType), w.setRequestHeader("Accept", p.dataTypes[0] && p.accepts[p.dataTypes[0]] ? p.accepts[p.dataTypes[0]] + ("*" !== p.dataTypes[0] ? ", " + Xn + "; q=0.01" : "") : p.accepts["*"]);
            for (o in p.headers) w.setRequestHeader(o, p.headers[o]);
            if (p.beforeSend && (p.beforeSend.call(d, w, p) === !1 || 2 === x)) return w.abort();
            T = "abort";
            for (o in {
                success: 1,
                error: 1,
                complete: 1
            }) w[o](p[o]);
            if (c = L(zn, p, n, w)) {
                w.readyState = 1, l && h.trigger("ajaxSend", [w, p]), p.async && p.timeout > 0 && (u = setTimeout(function () {
                    w.abort("timeout")
                }, p.timeout));
                try {
                    x = 1, c.send(v, r)
                } catch (N) {
                    if (!(2 > x)) throw N;
                    r(-1, N)
                }
            } else r(-1, "No Transport");
            return w
        },
        getScript: function (e, n) {
            return ut.get(e, t, n, "script")
        },
        getJSON: function (e, t, n) {
            return ut.get(e, t, n, "json")
        }
    }), ut.ajaxSetup({
        accepts: {
            script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
        },
        contents: {
            script: /(?:java|ecma)script/
        },
        converters: {
            "text script": function (e) {
                return ut.globalEval(e), e
            }
        }
    }), ut.ajaxPrefilter("script", function (e) {
        e.cache === t && (e.cache = !1), e.crossDomain && (e.type = "GET", e.global = !1)
    }), ut.ajaxTransport("script", function (e) {
        if (e.crossDomain) {
            var n, r = Y.head || ut("head")[0] || Y.documentElement;
            return {
                send: function (t, i) {
                    n = Y.createElement("script"), n.async = !0, e.scriptCharset && (n.charset = e.scriptCharset), n.src = e.url, n.onload = n.onreadystatechange = function (e, t) {
                        (t || !n.readyState || /loaded|complete/.test(n.readyState)) && (n.onload = n.onreadystatechange = null, n.parentNode && n.parentNode.removeChild(n), n = null, t || i(200, "success"))
                    }, r.insertBefore(n, r.firstChild)
                },
                abort: function () {
                    n && n.onload(t, !0)
                }
            }
        }
    });
    var Vn = [],
        Yn = /(=)\?(?=&|$)|\?\?/;
    ut.ajaxSetup({
        jsonp: "callback",
        jsonpCallback: function () {
            var e = Vn.pop() || ut.expando + "_" + qn++;
            return this[e] = !0, e
        }
    }), ut.ajaxPrefilter("json jsonp", function (n, r, i) {
        var o, a, s, u = n.jsonp !== !1 && (Yn.test(n.url) ? "url" : "string" == typeof n.data && !(n.contentType || "").indexOf("application/x-www-form-urlencoded") && Yn.test(n.data) && "data");
        return u || "jsonp" === n.dataTypes[0] ? (o = n.jsonpCallback = ut.isFunction(n.jsonpCallback) ? n.jsonpCallback() : n.jsonpCallback, u ? n[u] = n[u].replace(Yn, "$1" + o) : n.jsonp !== !1 && (n.url += (Mn.test(n.url) ? "&" : "?") + n.jsonp + "=" + o), n.converters["script json"] = function () {
            return s || ut.error(o + " was not called"), s[0]
        }, n.dataTypes[0] = "json", a = e[o], e[o] = function () {
            s = arguments
        }, i.always(function () {
            e[o] = a, n[o] && (n.jsonpCallback = r.jsonpCallback, Vn.push(o)), s && ut.isFunction(a) && a(s[0]), s = a = t
        }), "script") : t
    });
    var Jn, Gn, Qn = 0,
        Kn = e.ActiveXObject &&
            function () {
                var e;
                for (e in Jn) Jn[e](t, !0)
            };
    ut.ajaxSettings.xhr = e.ActiveXObject ?
        function () {
            return !this.isLocal && _() || F()
        } : _, Gn = ut.ajaxSettings.xhr(), ut.support.cors = !!Gn && "withCredentials" in Gn, Gn = ut.support.ajax = !!Gn, Gn && ut.ajaxTransport(function (n) {
        if (!n.crossDomain || ut.support.cors) {
            var r;
            return {
                send: function (i, o) {
                    var a, s, u = n.xhr();
                    if (n.username ? u.open(n.type, n.url, n.async, n.username, n.password) : u.open(n.type, n.url, n.async), n.xhrFields) for (s in n.xhrFields) u[s] = n.xhrFields[s];
                    n.mimeType && u.overrideMimeType && u.overrideMimeType(n.mimeType), n.crossDomain || i["X-Requested-With"] || (i["X-Requested-With"] = "XMLHttpRequest");
                    try {
                        for (s in i) u.setRequestHeader(s, i[s])
                    } catch (l) {
                    }
                   /* u.send(n.hasContent && n.data || null), r = function (e, i) {
                        var s, l, c, f;
                        try {
                            if (r && (i || 4 === u.readyState)) if (r = t, a && (u.onreadystatechange = ut.noop, Kn && delete Jn[a]), i) 4 !== u.readyState && u.abort();
                            else {
                                f = {}, s = u.status, l = u.getAllResponseHeaders(), "string" == typeof u.responseText && (f.text = u.responseText);
                                try {
                                    c = u.statusText
                                } catch (p) {
                                    c = ""
                                }
                                s || !n.isLocal || n.crossDomain ? 1223 === s && (s = 204) : s = f.text ? 200 : 404
                            }
                        } catch (d) {
                            i || o(-1, d)
                        }
                        f && o(s, c, f, l)
                    },*/ n.async ? 4 === u.readyState ? setTimeout(r) : (a = ++Qn, Kn && (Jn || (Jn = {}, ut(e).unload(Kn)), Jn[a] = r), u.onreadystatechange = r) : r()
                },
                abort: function () {
                    r && r(t, !0)
                }
            }
        }
    });
    var Zn, er, tr = /^(?:toggle|show|hide)$/,
        nr = RegExp("^(?:([+-])=|)(" + lt + ")([a-z%]*)$", "i"),
        rr = /queueHooks$/,
        ir = [W],
        or = {
            "*": [function (e, t) {
                var n, r, i = this.createTween(e, t),
                    o = nr.exec(t),
                    a = i.cur(),
                    s = +a || 0,
                    u = 1,
                    l = 20;
                if (o) {
                    if (n = +o[2], r = o[3] || (ut.cssNumber[e] ? "" : "px"), "px" !== r && s) {
                        s = ut.css(i.elem, e, !0) || n || 1;
                        do u = u || ".5", s /= u, ut.style(i.elem, e, s + r);
                        while (u !== (u = i.cur() / a) && 1 !== u && --l)
                    }
                    i.unit = r, i.start = s, i.end = o[1] ? s + (o[1] + 1) * n : n
                }
                return i
            }]
        };
    ut.Animation = ut.extend(P, {
        tweener: function (e, t) {
            ut.isFunction(e) ? (t = e, e = ["*"]) : e = e.split(" ");
            for (var n, r = 0, i = e.length; i > r; r++) n = e[r], or[n] = or[n] || [], or[n].unshift(t)
        },
        prefilter: function (e, t) {
            t ? ir.unshift(e) : ir.push(e)
        }
    }), ut.Tween = $, $.prototype = {
        constructor: $,
        init: function (e, t, n, r, i, o) {
            this.elem = e, this.prop = n, this.easing = i || "swing", this.options = t, this.start = this.now = this.cur(), this.end = r, this.unit = o || (ut.cssNumber[n] ? "" : "px")
        },
        cur: function () {
            var e = $.propHooks[this.prop];
            return e && e.get ? e.get(this) : $.propHooks._default.get(this)
        },
        run: function (e) {
            var t, n = $.propHooks[this.prop];
            return this.pos = t = this.options.duration ? ut.easing[this.easing](e, this.options.duration * e, 0, 1, this.options.duration) : e, this.now = (this.end - this.start) * t + this.start, this.options.step && this.options.step.call(this.elem, this.now, this), n && n.set ? n.set(this) : $.propHooks._default.set(this), this
        }
    }, $.prototype.init.prototype = $.prototype, $.propHooks = {
        _default: {
            get: function (e) {
                var t;
                return null == e.elem[e.prop] || e.elem.style && null != e.elem.style[e.prop] ? (t = ut.css(e.elem, e.prop, ""), t && "auto" !== t ? t : 0) : e.elem[e.prop]
            },
            set: function (e) {
                ut.fx.step[e.prop] ? ut.fx.step[e.prop](e) : e.elem.style && (null != e.elem.style[ut.cssProps[e.prop]] || ut.cssHooks[e.prop]) ? ut.style(e.elem, e.prop, e.now + e.unit) : e.elem[e.prop] = e.now
            }
        }
    }, $.propHooks.scrollTop = $.propHooks.scrollLeft = {
        set: function (e) {
            e.elem.nodeType && e.elem.parentNode && (e.elem[e.prop] = e.now)
        }
    }, ut.each(["toggle", "show", "hide"], function (e, t) {
        var n = ut.fn[t];
        ut.fn[t] = function (e, r, i) {
            return null == e || "boolean" == typeof e ? n.apply(this, arguments) : this.animate(I(t, !0), e, r, i)
        }
    }), ut.fn.extend({
        fadeTo: function (e, t, n, r) {
            return this.filter(w).css("opacity", 0).show().end().animate({
                opacity: t
            }, e, n, r)
        },
        animate: function (e, t, n, r) {
            var i = ut.isEmptyObject(e),
                o = ut.speed(t, n, r),
                a = function () {
                    var t = P(this, ut.extend({}, e), o);
                    a.finish = function () {
                        t.stop(!0)
                    }, (i || ut._data(this, "finish")) && t.stop(!0)
                };
            return a.finish = a, i || o.queue === !1 ? this.each(a) : this.queue(o.queue, a)
        },
        stop: function (e, n, r) {
            var i = function (e) {
                var t = e.stop;
                delete e.stop, t(r)
            };
            return "string" != typeof e && (r = n, n = e, e = t), n && e !== !1 && this.queue(e || "fx", []), this.each(function () {
                var t = !0,
                    n = null != e && e + "queueHooks",
                    o = ut.timers,
                    a = ut._data(this);
                if (n) a[n] && a[n].stop && i(a[n]);
                else for (n in a) a[n] && a[n].stop && rr.test(n) && i(a[n]);
                for (n = o.length; n--;) o[n].elem !== this || null != e && o[n].queue !== e || (o[n].anim.stop(r), t = !1, o.splice(n, 1));
                (t || !r) && ut.dequeue(this, e)
            })
        },
        finish: function (e) {
            return e !== !1 && (e = e || "fx"), this.each(function () {
                var t, n = ut._data(this),
                    r = n[e + "queue"],
                    i = n[e + "queueHooks"],
                    o = ut.timers,
                    a = r ? r.length : 0;
                for (n.finish = !0, ut.queue(this, e, []), i && i.cur && i.cur.finish && i.cur.finish.call(this), t = o.length; t--;) o[t].elem === this && o[t].queue === e && (o[t].anim.stop(!0), o.splice(t, 1));
                for (t = 0; a > t; t++) r[t] && r[t].finish && r[t].finish.call(this);
                delete n.finish
            })
        }
    }), ut.each({
        slideDown: I("show"),
        slideUp: I("hide"),
        slideToggle: I("toggle"),
        fadeIn: {
            opacity: "show"
        },
        fadeOut: {
            opacity: "hide"
        },
        fadeToggle: {
            opacity: "toggle"
        }
    }, function (e, t) {
        ut.fn[e] = function (e, n, r) {
            return this.animate(t, e, n, r)
        }
    }), ut.speed = function (e, t, n) {
        var r = e && "object" == typeof e ? ut.extend({}, e) : {
            complete: n || !n && t || ut.isFunction(e) && e,
            duration: e,
            easing: n && t || t && !ut.isFunction(t) && t
        };
        return r.duration = ut.fx.off ? 0 : "number" == typeof r.duration ? r.duration : r.duration in ut.fx.speeds ? ut.fx.speeds[r.duration] : ut.fx.speeds._default, (null == r.queue || r.queue === !0) && (r.queue = "fx"), r.old = r.complete, r.complete = function () {
            ut.isFunction(r.old) && r.old.call(this), r.queue && ut.dequeue(this, r.queue)
        }, r
    }, ut.easing = {
        linear: function (e) {
            return e
        },
        swing: function (e) {
            return .5 - Math.cos(e * Math.PI) / 2
        }
    }, ut.timers = [], ut.fx = $.prototype.init, ut.fx.tick = function () {
        var e, n = ut.timers,
            r = 0;
        for (Zn = ut.now(); n.length > r; r++) e = n[r], e() || n[r] !== e || n.splice(r--, 1);
        n.length || ut.fx.stop(), Zn = t
    }, ut.fx.timer = function (e) {
        e() && ut.timers.push(e) && ut.fx.start()
    }, ut.fx.interval = 13, ut.fx.start = function () {
        er || (er = setInterval(ut.fx.tick, ut.fx.interval))
    }, ut.fx.stop = function () {
        clearInterval(er), er = null
    }, ut.fx.speeds = {
        slow: 600,
        fast: 200,
        _default: 400
    }, ut.fx.step = {}, ut.expr && ut.expr.filters && (ut.expr.filters.animated = function (e) {
        return ut.grep(ut.timers, function (t) {
            return e === t.elem
        }).length
    }), ut.fn.offset = function (e) {
        if (arguments.length) return e === t ? this : this.each(function (t) {
            ut.offset.setOffset(this, e, t)
        });
        var n, r, i = {
                top: 0,
                left: 0
            },
            o = this[0],
            a = o && o.ownerDocument;
        return a ? (n = a.documentElement, ut.contains(n, o) ? (typeof o.getBoundingClientRect !== V && (i = o.getBoundingClientRect()), r = z(a), {
            top: i.top + (r.pageYOffset || n.scrollTop) - (n.clientTop || 0),
            left: i.left + (r.pageXOffset || n.scrollLeft) - (n.clientLeft || 0)
        }) : i) : void 0
    }, ut.offset = {
        setOffset: function (e, t, n) {
            var r = ut.css(e, "position");
            "static" === r && (e.style.position = "relative");
            var i, o, a = ut(e),
                s = a.offset(),
                u = ut.css(e, "top"),
                l = ut.css(e, "left"),
                c = ("absolute" === r || "fixed" === r) && ut.inArray("auto", [u, l]) > -1,
                f = {},
                p = {};
            c ? (p = a.position(), i = p.top, o = p.left) : (i = parseFloat(u) || 0, o = parseFloat(l) || 0), ut.isFunction(t) && (t = t.call(e, n, s)), null != t.top && (f.top = t.top - s.top + i), null != t.left && (f.left = t.left - s.left + o), "using" in t ? t.using.call(e, f) : a.css(f)
        }
    }, ut.fn.extend({
        position: function () {
            if (this[0]) {
                var e, t, n = {
                        top: 0,
                        left: 0
                    },
                    r = this[0];
                return "fixed" === ut.css(r, "position") ? t = r.getBoundingClientRect() : (e = this.offsetParent(), t = this.offset(), ut.nodeName(e[0], "html") || (n = e.offset()), n.top += ut.css(e[0], "borderTopWidth", !0), n.left += ut.css(e[0], "borderLeftWidth", !0)), {
                    top: t.top - n.top - ut.css(r, "marginTop", !0),
                    left: t.left - n.left - ut.css(r, "marginLeft", !0)
                }
            }
        },
        offsetParent: function () {
            return this.map(function () {
                for (var e = this.offsetParent || Y.documentElement; e && !ut.nodeName(e, "html") && "static" === ut.css(e, "position");) e = e.offsetParent;
                return e || Y.documentElement
            })
        }
    }), ut.each({
        scrollLeft: "pageXOffset",
        scrollTop: "pageYOffset"
    }, function (e, n) {
        var r = /Y/.test(n);
        ut.fn[e] = function (i) {
            return ut.access(this, function (e, i, o) {
                var a = z(e);
                return o === t ? a ? n in a ? a[n] : a.document.documentElement[i] : e[i] : (a ? a.scrollTo(r ? ut(a).scrollLeft() : o, r ? o : ut(a).scrollTop()) : e[i] = o, t)
            }, e, i, arguments.length, null)
        }
    }), ut.each({
        Height: "height",
        Width: "width"
    }, function (e, n) {
        ut.each({
            padding: "inner" + e,
            content: n,
            "": "outer" + e
        }, function (r, i) {
            ut.fn[i] = function (i, o) {
                var a = arguments.length && (r || "boolean" != typeof i),
                    s = r || (i === !0 || o === !0 ? "margin" : "border");
                return ut.access(this, function (n, r, i) {
                    var o;
                    return ut.isWindow(n) ? n.document.documentElement["client" + e] : 9 === n.nodeType ? (o = n.documentElement, Math.max(n.body["scroll" + e], o["scroll" + e], n.body["offset" + e], o["offset" + e], o["client" + e])) : i === t ? ut.css(n, r, s) : ut.style(n, r, i, s)
                }, n, a ? i : t, a, null)
            }
        })
    }), e.jQuery = e.$ = ut, "function" == typeof define && define.amd && define.amd.jQuery && define("jquery", [], function () {
        return ut
    })
}(window);
jQuery.easing.jswing = jQuery.easing.swing, jQuery.extend(jQuery.easing, {
    def: "easeOutQuad",
    swing: function (n, e, t, u, a) {
        return jQuery.easing[jQuery.easing.def](n, e, t, u, a)
    },
    easeInQuad: function (n, e, t, u, a) {
        return u * (e /= a) * e + t
    },
    easeOutQuad: function (n, e, t, u, a) {
        return -u * (e /= a) * (e - 2) + t
    },
    easeInOutQuad: function (n, e, t, u, a) {
        return (e /= a / 2) < 1 ? u / 2 * e * e + t : -u / 2 * (--e * (e - 2) - 1) + t
    },
    easeInCubic: function (n, e, t, u, a) {
        return u * (e /= a) * e * e + t
    },
    easeOutCubic: function (n, e, t, u, a) {
        return u * ((e = e / a - 1) * e * e + 1) + t
    },
    easeInOutCubic: function (n, e, t, u, a) {
        return (e /= a / 2) < 1 ? u / 2 * e * e * e + t : u / 2 * ((e -= 2) * e * e + 2) + t
    },
    easeInQuart: function (n, e, t, u, a) {
        return u * (e /= a) * e * e * e + t
    },
    easeOutQuart: function (n, e, t, u, a) {
        return -u * ((e = e / a - 1) * e * e * e - 1) + t
    },
    easeInOutQuart: function (n, e, t, u, a) {
        return (e /= a / 2) < 1 ? u / 2 * e * e * e * e + t : -u / 2 * ((e -= 2) * e * e * e - 2) + t
    },
    easeInQuint: function (n, e, t, u, a) {
        return u * (e /= a) * e * e * e * e + t
    },
    easeOutQuint: function (n, e, t, u, a) {
        return u * ((e = e / a - 1) * e * e * e * e + 1) + t
    },
    easeInOutQuint: function (n, e, t, u, a) {
        return (e /= a / 2) < 1 ? u / 2 * e * e * e * e * e + t : u / 2 * ((e -= 2) * e * e * e * e + 2) + t
    },
    easeInSine: function (n, e, t, u, a) {
        return -u * Math.cos(e / a * (Math.PI / 2)) + u + t
    },
    easeOutSine: function (n, e, t, u, a) {
        return u * Math.sin(e / a * (Math.PI / 2)) + t
    },
    easeInOutSine: function (n, e, t, u, a) {
        return -u / 2 * (Math.cos(Math.PI * e / a) - 1) + t
    },
    easeInExpo: function (n, e, t, u, a) {
        return 0 == e ? t : u * Math.pow(2, 10 * (e / a - 1)) + t
    },
    easeOutExpo: function (n, e, t, u, a) {
        return e == a ? t + u : u * (-Math.pow(2, -10 * e / a) + 1) + t
    },
    easeInOutExpo: function (n, e, t, u, a) {
        return 0 == e ? t : e == a ? t + u : (e /= a / 2) < 1 ? u / 2 * Math.pow(2, 10 * (e - 1)) + t : u / 2 * (-Math.pow(2, -10 * --e) + 2) + t
    },
    easeInCirc: function (n, e, t, u, a) {
        return -u * (Math.sqrt(1 - (e /= a) * e) - 1) + t
    },
    easeOutCirc: function (n, e, t, u, a) {
        return u * Math.sqrt(1 - (e = e / a - 1) * e) + t
    },
    easeInOutCirc: function (n, e, t, u, a) {
        return (e /= a / 2) < 1 ? -u / 2 * (Math.sqrt(1 - e * e) - 1) + t : u / 2 * (Math.sqrt(1 - (e -= 2) * e) + 1) + t
    },
    easeInElastic: function (n, e, t, u, a) {
        var r = 1.70158,
            i = 0,
            s = u;
        if (0 == e) return t;
        if (1 == (e /= a)) return t + u;
        if (i || (i = .3 * a), s < Math.abs(u)) {
            s = u;
            var r = i / 4
        } else var r = i / (2 * Math.PI) * Math.asin(u / s);
        return -(s * Math.pow(2, 10 * (e -= 1)) * Math.sin(2 * (e * a - r) * Math.PI / i)) + t
    },
    easeOutElastic: function (n, e, t, u, a) {
        var r = 1.70158,
            i = 0,
            s = u;
        if (0 == e) return t;
        if (1 == (e /= a)) return t + u;
        if (i || (i = .3 * a), s < Math.abs(u)) {
            s = u;
            var r = i / 4
        } else var r = i / (2 * Math.PI) * Math.asin(u / s);
        return s * Math.pow(2, -10 * e) * Math.sin(2 * (e * a - r) * Math.PI / i) + u + t
    },
    easeInOutElastic: function (n, e, t, u, a) {
        var r = 1.70158,
            i = 0,
            s = u;
        if (0 == e) return t;
        if (2 == (e /= a / 2)) return t + u;
        if (i || (i = .3 * a * 1.5), s < Math.abs(u)) {
            s = u;
            var r = i / 4
        } else var r = i / (2 * Math.PI) * Math.asin(u / s);
        return 1 > e ? -.5 * s * Math.pow(2, 10 * (e -= 1)) * Math.sin(2 * (e * a - r) * Math.PI / i) + t : s * Math.pow(2, -10 * (e -= 1)) * Math.sin(2 * (e * a - r) * Math.PI / i) * .5 + u + t
    },
    easeInBack: function (n, e, t, u, a, r) {
        return void 0 == r && (r = 1.70158), u * (e /= a) * e * ((r + 1) * e - r) + t
    },
    easeOutBack: function (n, e, t, u, a, r) {
        return void 0 == r && (r = 1.70158), u * ((e = e / a - 1) * e * ((r + 1) * e + r) + 1) + t
    },
    easeInOutBack: function (n, e, t, u, a, r) {
        return void 0 == r && (r = 1.70158), (e /= a / 2) < 1 ? u / 2 * e * e * (((r *= 1.525) + 1) * e - r) + t : u / 2 * ((e -= 2) * e * (((r *= 1.525) + 1) * e + r) + 2) + t
    },
    easeInBounce: function (n, e, t, u, a) {
        return u - jQuery.easing.easeOutBounce(n, a - e, 0, u, a) + t
    },
    easeOutBounce: function (n, e, t, u, a) {
        return (e /= a) < 1 / 2.75 ? 7.5625 * u * e * e + t : 2 / 2.75 > e ? u * (7.5625 * (e -= 1.5 / 2.75) * e + .75) + t : 2.5 / 2.75 > e ? u * (7.5625 * (e -= 2.25 / 2.75) * e + .9375) + t : u * (7.5625 * (e -= 2.625 / 2.75) * e + .984375) + t
    },
    easeInOutBounce: function (n, e, t, u, a) {
        return a / 2 > e ? .5 * jQuery.easing.easeInBounce(n, 2 * e, 0, u, a) + t : .5 * jQuery.easing.easeOutBounce(n, 2 * e - a, 0, u, a) + .5 * u + t
    }
});
!
    function (t) {
        "function" == typeof define && define.amd ? define(["jquery"], t) : t(jQuery)
    }(function (t) {
        var e = "ui-effects-",
            n = t;
        t.effects = {
            effect: {}
        }, function (t, e) {
            function n(t, e, n) {
                var r = l[e.type] || {};
                return null == t ? n || !e.def ? null : e.def : (t = r.floor ? ~~t : parseFloat(t), isNaN(t) ? e.def : r.mod ? (t + r.mod) % r.mod : 0 > t ? 0 : t > r.max ? r.max : t)
            }

            function r(n) {
                var r = f(),
                    o = r._rgba = [];
                return n = n.toLowerCase(), h(u, function (t, a) {
                    var i, s = a.re.exec(n),
                        u = s && a.parse(s),
                        f = a.space || "rgba";
                    return u ? (i = r[f](u), r[c[f].cache] = i[c[f].cache], o = r._rgba = i._rgba, !1) : e
                }), o.length ? ("0,0,0,0" === o.join() && t.extend(o, a.transparent), r) : a[n]
            }

            function o(t, e, n) {
                return n = (n + 1) % 1, 1 > 6 * n ? t + 6 * (e - t) * n : 1 > 2 * n ? e : 2 > 3 * n ? t + 6 * (e - t) * (2 / 3 - n) : t
            }

            var a, i = "backgroundColor borderBottomColor borderLeftColor borderRightColor borderTopColor color columnRuleColor outlineColor textDecorationColor textEmphasisColor",
                s = /^([\-+])=\s*(\d+\.?\d*)/,
                u = [{
                    re: /rgba?\(\s*(\d{1,3})\s*,\s*(\d{1,3})\s*,\s*(\d{1,3})\s*(?:,\s*(\d?(?:\.\d+)?)\s*)?\)/,
                    parse: function (t) {
                        return [t[1], t[2], t[3], t[4]]
                    }
                }, {
                    re: /rgba?\(\s*(\d+(?:\.\d+)?)\%\s*,\s*(\d+(?:\.\d+)?)\%\s*,\s*(\d+(?:\.\d+)?)\%\s*(?:,\s*(\d?(?:\.\d+)?)\s*)?\)/,
                    parse: function (t) {
                        return [2.55 * t[1], 2.55 * t[2], 2.55 * t[3], t[4]]
                    }
                }, {
                    re: /#([a-f0-9]{2})([a-f0-9]{2})([a-f0-9]{2})/,
                    parse: function (t) {
                        return [parseInt(t[1], 16), parseInt(t[2], 16), parseInt(t[3], 16)]
                    }
                }, {
                    re: /#([a-f0-9])([a-f0-9])([a-f0-9])/,
                    parse: function (t) {
                        return [parseInt(t[1] + t[1], 16), parseInt(t[2] + t[2], 16), parseInt(t[3] + t[3], 16)]
                    }
                }, {
                    re: /hsla?\(\s*(\d+(?:\.\d+)?)\s*,\s*(\d+(?:\.\d+)?)\%\s*,\s*(\d+(?:\.\d+)?)\%\s*(?:,\s*(\d?(?:\.\d+)?)\s*)?\)/,
                    space: "hsla",
                    parse: function (t) {
                        return [t[1], t[2] / 100, t[3] / 100, t[4]]
                    }
                }],
                f = t.Color = function (e, n, r, o) {
                    return new t.Color.fn.parse(e, n, r, o)
                },
                c = {
                    rgba: {
                        props: {
                            red: {
                                idx: 0,
                                type: "byte"
                            },
                            green: {
                                idx: 1,
                                type: "byte"
                            },
                            blue: {
                                idx: 2,
                                type: "byte"
                            }
                        }
                    },
                    hsla: {
                        props: {
                            hue: {
                                idx: 0,
                                type: "degrees"
                            },
                            saturation: {
                                idx: 1,
                                type: "percent"
                            },
                            lightness: {
                                idx: 2,
                                type: "percent"
                            }
                        }
                    }
                },
                l = {
                    "byte": {
                        floor: !0,
                        max: 255
                    },
                    percent: {
                        max: 1
                    },
                    degrees: {
                        mod: 360,
                        floor: !0
                    }
                },
                d = f.support = {},
                p = t("<p>")[0],
                h = t.each;
            p.style.cssText = "background-color:rgba(1,1,1,.5)", d.rgba = p.style.backgroundColor.indexOf("rgba") > -1, h(c, function (t, e) {
                e.cache = "_" + t, e.props.alpha = {
                    idx: 3,
                    type: "percent",
                    def: 1
                }
            }), f.fn = t.extend(f.prototype, {
                parse: function (o, i, s, u) {
                    if (o === e) return this._rgba = [null, null, null, null], this;
                    (o.jquery || o.nodeType) && (o = t(o).css(i), i = e);
                    var l = this,
                        d = t.type(o),
                        p = this._rgba = [];
                    return i !== e && (o = [o, i, s, u], d = "array"), "string" === d ? this.parse(r(o) || a._default) : "array" === d ? (h(c.rgba.props, function (t, e) {
                        p[e.idx] = n(o[e.idx], e)
                    }), this) : "object" === d ? (o instanceof f ? h(c, function (t, e) {
                        o[e.cache] && (l[e.cache] = o[e.cache].slice())
                    }) : h(c, function (e, r) {
                        var a = r.cache;
                        h(r.props, function (t, e) {
                            if (!l[a] && r.to) {
                                if ("alpha" === t || null == o[t]) return;
                                l[a] = r.to(l._rgba)
                            }
                            l[a][e.idx] = n(o[t], e, !0)
                        }), l[a] && 0 > t.inArray(null, l[a].slice(0, 3)) && (l[a][3] = 1, r.from && (l._rgba = r.from(l[a])))
                    }), this) : e
                },
                is: function (t) {
                    var n = f(t),
                        r = !0,
                        o = this;
                    return h(c, function (t, a) {
                        var i, s = n[a.cache];
                        return s && (i = o[a.cache] || a.to && a.to(o._rgba) || [], h(a.props, function (t, n) {
                            return null != s[n.idx] ? r = s[n.idx] === i[n.idx] : e
                        })), r
                    }), r
                },
                _space: function () {
                    var t = [],
                        e = this;
                    return h(c, function (n, r) {
                        e[r.cache] && t.push(n)
                    }), t.pop()
                },
                transition: function (t, e) {
                    var r = f(t),
                        o = r._space(),
                        a = c[o],
                        i = 0 === this.alpha() ? f("transparent") : this,
                        s = i[a.cache] || a.to(i._rgba),
                        u = s.slice();
                    return r = r[a.cache], h(a.props, function (t, o) {
                        var a = o.idx,
                            i = s[a],
                            f = r[a],
                            c = l[o.type] || {};
                        null !== f && (null === i ? u[a] = f : (c.mod && (f - i > c.mod / 2 ? i += c.mod : i - f > c.mod / 2 && (i -= c.mod)), u[a] = n((f - i) * e + i, o)))
                    }), this[o](u)
                },
                blend: function (e) {
                    if (1 === this._rgba[3]) return this;
                    var n = this._rgba.slice(),
                        r = n.pop(),
                        o = f(e)._rgba;
                    return f(t.map(n, function (t, e) {
                        return (1 - r) * o[e] + r * t
                    }))
                },
                toRgbaString: function () {
                    var e = "rgba(",
                        n = t.map(this._rgba, function (t, e) {
                            return null == t ? e > 2 ? 1 : 0 : t
                        });
                    return 1 === n[3] && (n.pop(), e = "rgb("), e + n.join() + ")"
                },
                toHslaString: function () {
                    var e = "hsla(",
                        n = t.map(this.hsla(), function (t, e) {
                            return null == t && (t = e > 2 ? 1 : 0), e && 3 > e && (t = Math.round(100 * t) + "%"), t
                        });
                    return 1 === n[3] && (n.pop(), e = "hsl("), e + n.join() + ")"
                },
                toHexString: function (e) {
                    var n = this._rgba.slice(),
                        r = n.pop();
                    return e && n.push(~~(255 * r)), "#" + t.map(n, function (t) {
                        return t = (t || 0).toString(16), 1 === t.length ? "0" + t : t
                    }).join("")
                },
                toString: function () {
                    return 0 === this._rgba[3] ? "transparent" : this.toRgbaString()
                }
            }), f.fn.parse.prototype = f.fn, c.hsla.to = function (t) {
                if (null == t[0] || null == t[1] || null == t[2]) return [null, null, null, t[3]];
                var e, n, r = t[0] / 255,
                    o = t[1] / 255,
                    a = t[2] / 255,
                    i = t[3],
                    s = Math.max(r, o, a),
                    u = Math.min(r, o, a),
                    f = s - u,
                    c = s + u,
                    l = .5 * c;
                return e = u === s ? 0 : r === s ? 60 * (o - a) / f + 360 : o === s ? 60 * (a - r) / f + 120 : 60 * (r - o) / f + 240, n = 0 === f ? 0 : .5 >= l ? f / c : f / (2 - c), [Math.round(e) % 360, n, l, null == i ? 1 : i]
            }, c.hsla.from = function (t) {
                if (null == t[0] || null == t[1] || null == t[2]) return [null, null, null, t[3]];
                var e = t[0] / 360,
                    n = t[1],
                    r = t[2],
                    a = t[3],
                    i = .5 >= r ? r * (1 + n) : r + n - r * n,
                    s = 2 * r - i;
                return [Math.round(255 * o(s, i, e + 1 / 3)), Math.round(255 * o(s, i, e)), Math.round(255 * o(s, i, e - 1 / 3)), a]
            }, h(c, function (r, o) {
                var a = o.props,
                    i = o.cache,
                    u = o.to,
                    c = o.from;
                f.fn[r] = function (r) {
                    if (u && !this[i] && (this[i] = u(this._rgba)), r === e) return this[i].slice();
                    var o, s = t.type(r),
                        l = "array" === s || "object" === s ? r : arguments,
                        d = this[i].slice();
                    return h(a, function (t, e) {
                        var r = l["object" === s ? t : e.idx];
                        null == r && (r = d[e.idx]), d[e.idx] = n(r, e)
                    }), c ? (o = f(c(d)), o[i] = d, o) : f(d)
                }, h(a, function (e, n) {
                    f.fn[e] || (f.fn[e] = function (o) {
                        var a, i = t.type(o),
                            u = "alpha" === e ? this._hsla ? "hsla" : "rgba" : r,
                            f = this[u](),
                            c = f[n.idx];
                        return "undefined" === i ? c : ("function" === i && (o = o.call(this, c), i = t.type(o)), null == o && n.empty ? this : ("string" === i && (a = s.exec(o), a && (o = c + parseFloat(a[2]) * ("+" === a[1] ? 1 : -1))), f[n.idx] = o, this[u](f)))
                    })
                })
            }), f.hook = function (e) {
                var n = e.split(" ");
                h(n, function (e, n) {
                    t.cssHooks[n] = {
                        set: function (e, o) {
                            var a, i, s = "";
                            if ("transparent" !== o && ("string" !== t.type(o) || (a = r(o)))) {
                                if (o = f(a || o), !d.rgba && 1 !== o._rgba[3]) {
                                    for (i = "backgroundColor" === n ? e.parentNode : e;
                                         ("" === s || "transparent" === s) && i && i.style;) try {
                                        s = t.css(i, "backgroundColor"), i = i.parentNode
                                    } catch (u) {
                                    }
                                    o = o.blend(s && "transparent" !== s ? s : "_default")
                                }
                                o = o.toRgbaString()
                            }
                            try {
                                e.style[n] = o
                            } catch (u) {
                            }
                        }
                    }, t.fx.step[n] = function (e) {
                        e.colorInit || (e.start = f(e.elem, n), e.end = f(e.end), e.colorInit = !0), t.cssHooks[n].set(e.elem, e.start.transition(e.end, e.pos))
                    }
                })
            }, f.hook(i), t.cssHooks.borderColor = {
                expand: function (t) {
                    var e = {};
                    return h(["Top", "Right", "Bottom", "Left"], function (n, r) {
                        e["border" + r + "Color"] = t
                    }), e
                }
            }, a = t.Color.names = {
                aqua: "#00ffff",
                black: "#000000",
                blue: "#0000ff",
                fuchsia: "#ff00ff",
                gray: "#808080",
                green: "#008000",
                lime: "#00ff00",
                maroon: "#800000",
                navy: "#000080",
                olive: "#808000",
                purple: "#800080",
                red: "#ff0000",
                silver: "#c0c0c0",
                teal: "#008080",
                white: "#ffffff",
                yellow: "#ffff00",
                transparent: [null, null, null, 0],
                _default: "#ffffff"
            }
        }(n), function () {
            function e(e) {
                var n, r, o = e.ownerDocument.defaultView ? e.ownerDocument.defaultView.getComputedStyle(e, null) : e.currentStyle,
                    a = {};
                if (o && o.length && o[0] && o[o[0]]) for (r = o.length; r--;) n = o[r], "string" == typeof o[n] && (a[t.camelCase(n)] = o[n]);
                else for (n in o)"string" == typeof o[n] && (a[n] = o[n]);
                return a
            }

            function r(e, n) {
                var r, o, i = {};
                for (r in n) o = n[r], e[r] !== o && (a[r] || (t.fx.step[r] || !isNaN(parseFloat(o))) && (i[r] = o));
                return i
            }

            var o = ["add", "remove", "toggle"],
                a = {
                    border: 1,
                    borderBottom: 1,
                    borderColor: 1,
                    borderLeft: 1,
                    borderRight: 1,
                    borderTop: 1,
                    borderWidth: 1,
                    margin: 1,
                    padding: 1
                };
            t.each(["borderLeftStyle", "borderRightStyle", "borderBottomStyle", "borderTopStyle"], function (e, r) {
                t.fx.step[r] = function (t) {
                    ("none" !== t.end && !t.setAttr || 1 === t.pos && !t.setAttr) && (n.style(t.elem, r, t.end), t.setAttr = !0)
                }
            }), t.fn.addBack || (t.fn.addBack = function (t) {
                return this.add(null == t ? this.prevObject : this.prevObject.filter(t))
            }), t.effects.animateClass = function (n, a, i, s) {
                var u = t.speed(a, i, s);
                return this.queue(function () {
                    var a, i = t(this),
                        s = i.attr("class") || "",
                        f = u.children ? i.find("*").addBack() : i;
                    f = f.map(function () {
                        var n = t(this);
                        return {
                            el: n,
                            start: e(this)
                        }
                    }), a = function () {
                        t.each(o, function (t, e) {
                            n[e] && i[e + "Class"](n[e])
                        })
                    }, a(), f = f.map(function () {
                        return this.end = e(this.el[0]), this.diff = r(this.start, this.end), this
                    }), i.attr("class", s), f = f.map(function () {
                        var e = this,
                            n = t.Deferred(),
                            r = t.extend({}, u, {
                                queue: !1,
                                complete: function () {
                                    n.resolve(e)
                                }
                            });
                        return this.el.animate(this.diff, r), n.promise()
                    }), t.when.apply(t, f.get()).done(function () {
                        a(), t.each(arguments, function () {
                            var e = this.el;
                            t.each(this.diff, function (t) {
                                e.css(t, "")
                            })
                        }), u.complete.call(i[0])
                    })
                })
            }, t.fn.extend({
                addClass: function (e) {
                    return function (n, r, o, a) {
                        return r ? t.effects.animateClass.call(this, {
                            add: n
                        }, r, o, a) : e.apply(this, arguments)
                    }
                }(t.fn.addClass),
                removeClass: function (e) {
                    return function (n, r, o, a) {
                        return arguments.length > 1 ? t.effects.animateClass.call(this, {
                            remove: n
                        }, r, o, a) : e.apply(this, arguments)
                    }
                }(t.fn.removeClass),
                toggleClass: function (e) {
                    return function (n, r, o, a, i) {
                        return "boolean" == typeof r || void 0 === r ? o ? t.effects.animateClass.call(this, r ? {
                            add: n
                        } : {
                            remove: n
                        }, o, a, i) : e.apply(this, arguments) : t.effects.animateClass.call(this, {
                            toggle: n
                        }, r, o, a)
                    }
                }(t.fn.toggleClass),
                switchClass: function (e, n, r, o, a) {
                    return t.effects.animateClass.call(this, {
                        add: n,
                        remove: e
                    }, r, o, a)
                }
            })
        }(), function () {
            function n(e, n, r, o) {
                return t.isPlainObject(e) && (n = e, e = e.effect), e = {
                    effect: e
                }, null == n && (n = {}), t.isFunction(n) && (o = n, r = null, n = {}), ("number" == typeof n || t.fx.speeds[n]) && (o = r, r = n, n = {}), t.isFunction(r) && (o = r, r = null), n && t.extend(e, n), r = r || n.duration, e.duration = t.fx.off ? 0 : "number" == typeof r ? r : r in t.fx.speeds ? t.fx.speeds[r] : t.fx.speeds._default, e.complete = o || n.complete, e
            }

            function r(e) {
                return !e || "number" == typeof e || t.fx.speeds[e] ? !0 : "string" != typeof e || t.effects.effect[e] ? t.isFunction(e) ? !0 : "object" != typeof e || e.effect ? !1 : !0 : !0
            }

            t.extend(t.effects, {
                version: "1.11.2",
                save: function (t, n) {
                    for (var r = 0; n.length > r; r++) null !== n[r] && t.data(e + n[r], t[0].style[n[r]])
                },
                restore: function (t, n) {
                    var r, o;
                    for (o = 0; n.length > o; o++) null !== n[o] && (r = t.data(e + n[o]), void 0 === r && (r = ""), t.css(n[o], r))
                },
                setMode: function (t, e) {
                    return "toggle" === e && (e = t.is(":hidden") ? "show" : "hide"), e
                },
                getBaseline: function (t, e) {
                    var n, r;
                    switch (t[0]) {
                        case "top":
                            n = 0;
                            break;
                        case "middle":
                            n = .5;
                            break;
                        case "bottom":
                            n = 1;
                            break;
                        default:
                            n = t[0] / e.height
                    }
                    switch (t[1]) {
                        case "left":
                            r = 0;
                            break;

                        case "center":
                            r = .5;
                            break;
                        case "right":
                            r = 1;
                            break;
                        default:
                            r = t[1] / e.width
                    }
                    return {
                        x: r,
                        y: n
                    }
                },
                createWrapper: function (e) {
                    if (e.parent().is(".ui-effects-wrapper")) return e.parent();
                    var n = {
                            width: e.outerWidth(!0),
                            height: e.outerHeight(!0),
                            "float": e.css("float")
                        },
                        r = t("<div></div>").addClass("ui-effects-wrapper").css({
                            fontSize: "100%",
                            background: "transparent",
                            border: "none",
                            margin: 0,
                            padding: 0
                        }),
                        o = {
                            width: e.width(),
                            height: e.height()
                        },
                        a = document.activeElement;
                    try {
                        a.id
                    } catch (i) {
                        a = document.body
                    }
                    return e.wrap(r), (e[0] === a || t.contains(e[0], a)) && t(a).focus(), r = e.parent(), "static" === e.css("position") ? (r.css({
                        position: "relative"
                    }), e.css({
                        position: "relative"
                    })) : (t.extend(n, {
                        position: e.css("position"),
                        zIndex: e.css("z-index")
                    }), t.each(["top", "left", "bottom", "right"], function (t, r) {
                        n[r] = e.css(r), isNaN(parseInt(n[r], 10)) && (n[r] = "auto")
                    }), e.css({
                        position: "relative",
                        top: 0,
                        left: 0,
                        right: "auto",
                        bottom: "auto"
                    })), e.css(o), r.css(n).show()
                },
                removeWrapper: function (e) {
                    var n = document.activeElement;
                    return e.parent().is(".ui-effects-wrapper") && (e.parent().replaceWith(e), (e[0] === n || t.contains(e[0], n)) && t(n).focus()), e
                },
                setTransition: function (e, n, r, o) {
                    return o = o || {}, t.each(n, function (t, n) {
                        var a = e.cssUnit(n);
                        a[0] > 0 && (o[n] = a[0] * r + a[1])
                    }), o
                }
            }), t.fn.extend({
                effect: function () {
                    function e(e) {
                        function n() {
                            t.isFunction(a) && a.call(o[0]), t.isFunction(e) && e()
                        }

                        var o = t(this),
                            a = r.complete,
                            s = r.mode;
                        (o.is(":hidden") ? "hide" === s : "show" === s) ? (o[s](), n()) : i.call(o[0], r, n)
                    }

                    var r = n.apply(this, arguments),
                        o = r.mode,
                        a = r.queue,
                        i = t.effects.effect[r.effect];
                    return t.fx.off || !i ? o ? this[o](r.duration, r.complete) : this.each(function () {
                        r.complete && r.complete.call(this)
                    }) : a === !1 ? this.each(e) : this.queue(a || "fx", e)
                },
                show: function (t) {
                    return function (e) {
                        if (r(e)) return t.apply(this, arguments);
                        var o = n.apply(this, arguments);
                        return o.mode = "show", this.effect.call(this, o)
                    }
                }(t.fn.show),
                hide: function (t) {
                    return function (e) {
                        if (r(e)) return t.apply(this, arguments);
                        var o = n.apply(this, arguments);
                        return o.mode = "hide", this.effect.call(this, o)
                    }
                }(t.fn.hide),
                toggle: function (t) {
                    return function (e) {
                        if (r(e) || "boolean" == typeof e) return t.apply(this, arguments);
                        var o = n.apply(this, arguments);
                        return o.mode = "toggle", this.effect.call(this, o)
                    }
                }(t.fn.toggle),
                cssUnit: function (e) {
                    var n = this.css(e),
                        r = [];
                    return t.each(["em", "px", "%", "pt"], function (t, e) {
                        n.indexOf(e) > 0 && (r = [parseFloat(n), e])
                    }), r
                }
            })
        }(), function () {
            var e = {};
            t.each(["Quad", "Cubic", "Quart", "Quint", "Expo"], function (t, n) {
                e[n] = function (e) {
                    return Math.pow(e, t + 2)
                }
            }), t.extend(e, {
                Sine: function (t) {
                    return 1 - Math.cos(t * Math.PI / 2)
                },
                Circ: function (t) {
                    return 1 - Math.sqrt(1 - t * t)
                },
                Elastic: function (t) {
                    return 0 === t || 1 === t ? t : -Math.pow(2, 8 * (t - 1)) * Math.sin((80 * (t - 1) - 7.5) * Math.PI / 15)
                },
                Back: function (t) {
                    return t * t * (3 * t - 2)
                },
                Bounce: function (t) {
                    for (var e, n = 4;
                         ((e = Math.pow(2, --n)) - 1) / 11 > t;);
                    return 1 / Math.pow(4, 3 - n) - 7.5625 * Math.pow((3 * e - 2) / 22 - t, 2)
                }
            }), t.each(e, function (e, n) {
                t.easing["easeIn" + e] = n, t.easing["easeOut" + e] = function (t) {
                    return 1 - n(1 - t)
                }, t.easing["easeInOut" + e] = function (t) {
                    return .5 > t ? n(2 * t) / 2 : 1 - n(-2 * t + 2) / 2
                }
            })
        }(), t.effects
    });
$(function () {
    var t = $("[rel~=tooltip]"),
        e = !1,
        i = !1;
    t.bind("mouseenter", function () {
        if (e = $(this), tip = e.attr("title"), white = e.attr("name"), i = $('<div class="tooltip"></div>'), !tip || "" == tip) return !1;
        e.removeAttr("title"), i.css("opacity", 0).html(tip).appendTo("body");
        var t = function () {
            $(window).width() < 1.5 * i.outerWidth() ? i.css("max-width", $(window).width() / 2) : i.css("max-width", 340);
            var t = e.offset().left + e.outerWidth() / 2 - i.outerWidth() / 2,
                o = e.offset().top - i.outerHeight() - 20;
            if (0 > t ? (t = e.offset().left + e.outerWidth() / 2 - 20, i.addClass("left")) : i.removeClass("left"), t + i.outerWidth() > $(window).width() ? (t = e.offset().left - i.outerWidth() + e.outerWidth() / 2 + 20, i.addClass("right")) : i.removeClass("right"), 0 > o) {
                var o = e.offset().top + e.outerHeight() + 10;
                i.addClass("top")
            } else i.removeClass("top");
            i.css({
                left: t,
                top: o
            }).animate({
                top: "+=10",
                opacity: 1
            }, 50)
        };
        t(), $(window).resize(t);
        var o = function () {
            i.animate({
                top: "-=10",
                opacity: 0
            }, 50, function () {
                $(this).remove()
            }), e.attr("title", tip)
        };
        e.bind("mouseleave", o), i.bind("click", o)
    })
});
!
    function (t) {
        t.fn.extend({
            changeClass: function (i, n) {
                this.click(function () {
                    t(this).siblings().removeClass(i), t(this).siblings().removeClass(n), t(this).addClass(i)
                })
            },
            menuSlide: function (i, n, e, s) {
                if (0 == t(this).length) return !1;
                var h, o, a = t(n),
                    r = a.closest("ul").children("li"),
                    l = t(this).parent().find("li").eq(0);
                0 == t(i).length ? (h = l.position().left + e, o = l.width()) : (h = t(i).position().left + e, o = t(i).width());
                var c = h,
                    f = o;
                a.css({
                    left: h,
                    width: o
                }).show(), r.click(function () {
                    t(this).siblings().removeClass(i), t(this).addClass(i), c = t(this).position().left + e, f = t(this).width(), a.stop().animate({
                        left: c,
                        width: f
                    }, s)
                }), r.hover(function () {
                    o = t(this).width(), h = t(this).position().left + e, a.stop().animate({
                        left: h,
                        width: o
                    }, s)
                }, function () {
                    a.stop().animate({
                        left: c,
                        width: f
                    }, s)
                })
            },
            carousel: function (i) {
                function n() {
                    t(i.nextBtn).click()
                }

                var e, s = {
                        prevBtn: ".prev-btn",
                        nextBtn: ".next-btn",
                        speed: 1e3,
                        autoSlide: !1,
                        spaceTime: 2e3,
                        showDot: !1
                    },
                    i = t.extend(s, i),
                    h = this,
                    o = (h.children().size(), Number(h.children().width()));
                h.css("margin-left", -o), t(i.nextBtn).click(function () {
                    h.stop().animate({
                        "margin-left": 2 * -o
                    }, i.speed, function () {
                        h.children().eq(0).appendTo(h), h.css("margin-left", -o)
                    })
                }), t(i.prevBtn).click(function () {
                    h.children().first().stop().animate({
                        "margin-left": o
                    }, i.speed, function () {
                        h.children().last().prependTo(h), t(this).css("margin-left", 0)
                    })
                }), i.autoSlide && (e = setInterval(n, i.spaceTime), h.hover(function () {
                    clearInterval(e)
                }, function () {
                    e = setInterval(n, i.spaceTime)
                }))
            },
            countdown: function (t, i) {
                var n = this,
                    e = !1,
                    s = function () {
                        var s = 0,
                            h = 0,
                            o = 0,
                            a = 0;
                        t > 0 && (s = Math.floor(t / 86400), h = Math.floor(t / 3600) - 24 * s, o = Math.floor(t / 60) - 24 * s * 60 - 60 * h, a = Math.floor(t) - 24 * s * 60 * 60 - 60 * h * 60 - 60 * o), 9 >= o && (o = "0" + o), 9 >= a && (a = "0" + a), n.find(".day_show").html(s), n.find(".hour_show").html(h), n.find(".minute_show").html(o), n.find(".second_show").html(a), t--, i && !e && (i(), e = !0)
                    };
                s(), window.setInterval(function () {
                    s()
                }, 1e3)
            },
            addreduce: function (i, n, e) {
                var s = 1,
                    h = this.find(".add-btn"),
                    o = this.find(".reduce-btn");
                h.click(function () {
                    var h = Number(t(this).parent().find(".total-val").html());
                    return s = h, i > s ? (s += 1, t(this).parent().find(".total-val").html(s), e(), void 0) : (n && n(), !1)
                }), o.click(function () {
                    var i = Number(t(this).parent().find(".total-val").html());
                    return s = i, s > 1 ? (s -= 1, t(this).parent().find(".total-val").html(s), e(), void 0) : !1
                })
            },
            textareaAutoHeight: function (i) {
                this._options = {
                    minHeight: 0,
                    maxHeight: 1e3
                }, this.init = function () {
                    for (var n in i) this._options[n] = i[n];
                    0 == this._options.minHeight && (this._options.minHeight = parseFloat(t(this).height()));
                    for (var n in this._options) null == t(this).attr(n) && t(this).attr(n, this._options[n]);
                    t(this).keyup(this.resetHeight).change(this.resetHeight).focus(this.resetHeight)
                }, this.resetHeight = function () {
                    var i = parseFloat(t(this).attr("minHeight")),
                        n = parseFloat(t(this).attr("maxHeight"));
                    window.addEventListener && t(this).height(0);
                    var e = parseFloat(this.scrollHeight);
                    e = i > e ? i : e > n ? n : e, t(this).height(e).scrollTop(e)
                }, this.init()
            },
            maxLength: function (t, i) {
                var n = this;
                n.unbind("input propertychange change"), n.bind("input propertychange change", function () {
                    var e = n.val().toString();
                    e.length > t && (n.val(e.substring(0, t)), i && i())
                })
            }
        })
    }(jQuery);
!
    function (e, t) {
        "use strict";
        var o, i, a = "/static/js/",
            s = {
                getPath: function () {
                    var e = document.scripts,
                        t = e[e.length - 1].src;
                    return a ? a : t.substring(0, t.lastIndexOf("/") + 1)
                },
                type: ["dialog", "page", "iframe", "loading", "tips"]
            };
        e.layer = {
            v: "1.8.5",
            ie6: !!e.ActiveXObject && !e.XMLHttpRequest,
            index: 0,
            path: s.getPath(),
            use: function (e, t) {
                var i = o("head")[0],
                    e = e.replace(/\s/g, ""),
                    a = /\.css$/.test(e),
                    s = document.createElement(a ? "link" : "script"),
                    r = e.replace(/\.|\//g, "");
                a && (s.type = "text/css", s.rel = "stylesheet"), s[a ? "href" : "src"] = /^http:\/\//.test(e) ? e : layer.path + e, s.id = r, o("#" + r)[0] || i.appendChild(s), t && (document.all ? o(s).ready(t) : o(s).load(t))
            },
            alert: function (e, t, i, a) {
                var s = "function" == typeof i,
                    r = {
                        dialog: {
                            msg: e,
                            type: t,
                            yes: s ? i : a
                        },
                        area: ["auto", "auto"]
                    };
                return s || (r.title = i), o.layer(r)
            },
            confirm: function (e, t, i, a) {
                var s = "function" == typeof i,
                    r = {
                        dialog: {
                            msg: e,
                            type: 4,
                            btns: 2,
                            yes: t,
                            no: s ? i : a
                        }
                    };
                return s || (r.title = i), o.layer(r)
            },
            msg: function (e, i, a, s) {
                var r = {
                    title: !1,
                    closeBtn: !1,
                    time: i === t ? 2 : i,
                    dialog: {
                        msg: "" === e || e === t ? "&nbsp;" : e
                    },
                    end: s
                };
                return "object" == typeof a ? (r.dialog.type = a.type, r.shade = a.shade, r.shift = a.rate) : "function" == typeof a ? r.end = a : r.dialog.type = a, o.layer(r)
            },
            load: function (e, t) {
                return "string" == typeof e ? layer.msg(e, t || 0, 16) : o.layer({
                    time: e,
                    loading: {
                        type: t
                    },
                    bgcolor: t ? "#fff" : "",
                    shade: t ? [.1, "#000"] : [0],
                    border: 3 !== t && t ? [6, .3, "#000"] : [0],
                    type: 3,
                    title: ["", !1],
                    closeBtn: [0, !1]
                })
            },
            tips: function (e, t, i, a, s, r) {
                var n = {
                    type: 4,
                    shade: !1,
                    success: function (e) {
                        this.closeBtn || e.find(".xubox_tips").css({
                            "padding-right": 10
                        })
                    },
                    bgcolor: "",
                    tips: {
                        msg: e,
                        follow: t
                    }
                };
                return n.time = "object" == typeof i ? i.time : 0 | i, i = i || {}, n.closeBtn = i.closeBtn || !1, n.maxWidth = i.maxWidth || a, n.tips.guide = i.guide || s, n.tips.style = i.style || r, n.tips.more = i.more, o.layer(n)
            }
        };
        var r = ["xubox_layer", "xubox_iframe", ".xubox_title", ".xubox_text", ".xubox_page", ".xubox_main"],
            n = function (e) {
                var t = this,
                    i = t.config;
                layer.index++, t.index = layer.index, t.config = o.extend({}, i, e), t.config.dialog = o.extend({}, i.dialog, e.dialog), t.config.page = o.extend({}, i.page, e.page), t.config.iframe = o.extend({}, i.iframe, e.iframe), t.config.loading = o.extend({}, i.loading, e.loading), t.config.tips = o.extend({}, i.tips, e.tips), t.creat()
            };
        n.pt = n.prototype, n.pt.config = {
            type: 0,
            shade: [.3, "#000"],
            fix: !0,
            move: ".xubox_title",
            title: "&#x4FE1;&#x606F;",
            offset: ["", "50%"],
            area: ["310px", "auto"],
            closeBtn: [0, !0],
            time: 0,
            fadeIn: 300,
            bgcolor: "#fff",
            border: [6, .3, "#000"],
            zIndex: 19891014,
            maxWidth: 400,
            dialog: {
                btns: 1,
                btn: ["&#x786E;&#x5B9A;", "&#x53D6;&#x6D88;"],
                type: 8,
                msg: "",
                yes: function (e) {
                    layer.close(e)
                },
                no: function (e) {
                    layer.close(e)
                }
            },
            page: {
                dom: "#xulayer",
                html: "",
                url: ""
            },
            iframe: {
                src: "http://sentsin.com",
                scrolling: "auto"
            },
            loading: {
                type: 0
            },
            tips: {
                msg: "",
                follow: "",
                guide: 0,
                isGuide: !0,
                style: ["background-color:#ff004e; color:#fff;", "#ff004e"]
            },
            success: function () {
            },
            close: function (e) {
                layer.close(e)
            },
            end: function () {
            }
        }, n.pt.space = function (e) {
            var t = this,
                e = e || "",
                o = t.index,
                i = t.config,
                a = i.dialog,
                s = -1 === a.type ? "" : '<span class="xubox_msg xulayer_png32 xubox_msgico xubox_msgtype' + a.type + '"></span>',
                n = ['<div class="xubox_dialog">' + s + '<span class="xubox_msg xubox_text" style="' + (s ? "" : "padding-left:20px") + '">' + a.msg + "</span></div>", '<div class="xubox_page">' + e + "</div>", '<iframe scrolling="' + i.iframe.scrolling + '" allowtransparency="true" id="' + r[1] + o + '" name="' + r[1] + o + '" onload="this.className=\'' + r[1] + '\'" class="' + r[1] + '" frameborder="0" src="' + i.iframe.src + '"></iframe>', '<span class="xubox_loading xubox_loading_' + i.loading.type + '"></span>', '<div class="xubox_tips" style="' + i.tips.style[0] + '"><div class="xubox_tipsMsg">' + i.tips.msg + '</div><i class="layerTipsG"></i></div>'],
                l = "",
                c = "",
                f = i.zIndex + o,
                d = "z-index:" + f + "; background-color:" + i.shade[1] + "; opacity:" + i.shade[0] + "; filter:alpha(opacity=" + 100 * i.shade[0] + ");";
            i.shade[0] && (l = '<div times="' + o + '" id="xubox_shade' + o + '" class="xubox_shade" style="' + d + '"></div>'), i.zIndex = f;
            var p = "",
                u = "",
                h = "z-index:" + (f - 1) + ";  background-color: " + i.border[2] + "; opacity:" + i.border[1] + "; filter:alpha(opacity=" + 100 * i.border[1] + "); top:-" + i.border[0] + "px; left:-" + i.border[0] + "px;";
            i.border[0] && (c = '<div id="xubox_border' + o + '" class="xubox_border" style="' + h + '"></div>'), !i.maxmin || 1 !== i.type && 2 !== i.type || /^\d+%$/.test(i.area[0]) && /^\d+%$/.test(i.area[1]) || (u = '<a class="xubox_min" href="javascript:;"><cite></cite></a><a class="xubox_max xulayer_png32" href="javascript:;"></a>'), i.closeBtn[1] && (u += '<a class="xubox_close xulayer_png32 xubox_close' + i.closeBtn[0] + '" href="javascript:;" style="' + (4 === i.type ? "position:absolute; right:-3px; _right:7px; top:-4px;" : "") + '"></a>');
            var x = "object" == typeof i.title;
            return i.title && (p = '<div class="xubox_title" style="' + (x ? i.title[1] : "") + '"><em>' + (x ? i.title[0] : i.title) + "</em></div>"), [l, '<div times="' + o + '" showtime="' + i.time + '" style="z-index:' + (f + 1) + '" id="' + r[0] + o + '" class="' + r[0] + '"><div style="z-index:' + f + '" class="xubox_main">' + n[i.type] + p + '<span class="xubox_setwin">' + u + '</span><span class="xubox_botton"></span></div>' + c + "</div>"]
        }, n.pt.creat = function () {
            var e = this,
                t = "",
                i = e.config,
                a = i.dialog,
                s = e.index,
                n = i.page,
                l = o("body"),
                c = function (i) {
                    var i = i || "";
                    t = e.space(i), l.append(o(t[0]))
                };
            switch (i.type) {
                case 0:
                    i.title || (i.area = ["auto", "auto"]), o(".xubox_dialog")[0] && layer.close(o(".xubox_dialog").parents("." + r[0]).attr("times"));
                    break;
                case 1:
                    if ("" !== n.html) c('<div class="xuboxPageHtml">' + n.html + "</div>"), l.append(o(t[1]));
                    else if ("" !== n.url) c('<div class="xuboxPageHtml" id="xuboxPageHtml' + s + '">' + n.html + "</div>"), l.append(o(t[1])), o.get(n.url, function (e) {
                        o("#xuboxPageHtml" + s).html(e.toString()), n.ok && n.ok(e)
                    });
                    else {
                        if (0 != o(n.dom).parents(r[4]).length) return;
                        c(), o(n.dom).show().wrap(o(t[1]))
                    }
                    break;
                case 3:
                    i.title = !1, i.area = ["auto", "auto"], i.closeBtn = ["", !1], o(".xubox_loading")[0] && layer.closeLoad();
                    break;
                case 4:
                    i.title = !1, i.area = ["auto", "auto"], i.fix = !1, i.border = [0], i.tips.more || layer.closeTips()
            }
            1 !== i.type && (c(), l.append(o(t[1])));
            var f = e.layerE = o("#" + r[0] + s);
            if (f.css({
                    width: i.area[0],
                    height: i.area[1]
                }), i.fix || f.css({
                    position: "absolute"
                }), i.title && (3 !== i.type || 4 !== i.type)) {
                var d = 0 === i.type ? a : i,
                    p = f.find(".xubox_botton");
                switch (d.btn = i.btn || a.btn, d.btns) {
                    case 0:
                        p.html("").hide();
                        break;
                    case 1:
                        p.html('<a href="javascript:;" class="xubox_yes xubox_botton1">' + d.btn[0] + "</a>");
                        break;
                    case 2:
                        p.html('<a href="javascript:;" class="xubox_yes xubox_botton2">' + d.btn[0] + '</a><a href="javascript:;" class="xubox_no xubox_botton3">' + d.btn[1] + "</a>")
                }
            }
            "auto" === f.css("left") ? (f.hide(), setTimeout(function () {
                f.show(), e.set(s)
            }, 500)) : e.set(s), i.time <= 0 || e.autoclose(), e.callback()
        }, s.fade = function (e, t, o) {
            e.css({
                opacity: 0
            }).animate({
                opacity: o
            }, t)
        }, n.pt.offset = function () {
            var e = this,
                t = e.config,
                o = e.layerE,
                a = o.outerHeight();
            e.offsetTop = "" === t.offset[0] && a < i.height() ? (i.height() - a - 2 * t.border[0]) / 2 : -1 != t.offset[0].indexOf("px") ? parseFloat(t.offset[0]) : parseFloat(t.offset[0] || 0) / 100 * i.height(), e.offsetTop = e.offsetTop + t.border[0] + (t.fix ? 0 : i.scrollTop()), -1 != t.offset[1].indexOf("px") ? e.offsetLeft = parseFloat(t.offset[1]) + t.border[0] : (t.offset[1] = "" === t.offset[1] ? "50%" : t.offset[1], e.offsetLeft = "50%" === t.offset[1] ? t.offset[1] : parseFloat(t.offset[1]) / 100 * i.width() + t.border[0])
        }, n.pt.set = function (e) {
            var t = this,
                a = t.config,
                n = (a.dialog, a.page),
                l = (a.loading, t.layerE),
                c = l.find(r[2]);
            switch (t.autoArea(e), a.title ? 0 === a.type && layer.ie6 && c.css({
                width: l.outerWidth()
            }) : 4 !== a.type && l.find(".xubox_close").addClass("xubox_close1"), l.attr({
                type: s.type[a.type]
            }), t.offset(), 4 !== a.type && (a.shift && !layer.ie6 ? "object" == typeof a.shift ? t.shift(a.shift[0], a.shift[1] || 500, a.shift[2]) : t.shift(a.shift, 500) : l.css({
                top: t.offsetTop,
                left: t.offsetLeft
            })), a.type) {
                case 0:
                    l.find(r[5]).css({
                        "background-color": "#fff"
                    }), a.title ? l.find(r[3]).css({
                        paddingTop: 18 + c.outerHeight()
                    }) : (l.find(".xubox_msgico").css({
                        top: "50%",
                        marginTop: -16
                    }), l.find(r[3]).css({
                        marginTop: 11
                    }));
                    break;
                case 1:
                    l.find(n.dom).addClass("layer_pageContent"), a.shade[0] && l.css({
                        zIndex: a.zIndex + 1
                    }), a.title && l.find(r[4]).css({
                        top: c.outerHeight()
                    });
                    break;
                case 2:
                    var f = l.find("." + r[1]),
                        d = l.height();
                    f.addClass("xubox_load").css({
                        width: l.width()
                    }), f.css(a.title ? {
                        top: c.height(),
                        height: d - c.height()
                    } : {
                        top: 0,
                        height: d
                    }), layer.ie6 && f.attr("src", a.iframe.src);
                    break;
                case 4:
                    var p = [0, l.outerHeight()],
                        u = o(a.tips.follow),
                        h = {
                            width: u.outerWidth(),
                            height: u.outerHeight(),
                            top: u.offset().top,
                            left: u.offset().left
                        },
                        x = l.find(".layerTipsG");
                    a.tips.isGuide || x.remove(), l.outerWidth() > a.maxWidth && l.width(a.maxWidth), h.tipColor = a.tips.style[1], p[0] = l.outerWidth(), h.autoLeft = function () {
                        h.left + p[0] - i.width() > 0 ? (h.tipLeft = h.left + h.width - p[0], x.css({
                            right: 12,
                            left: "auto"
                        })) : h.tipLeft = h.left
                    }, h.where = [function () {
                        h.autoLeft(), h.tipTop = h.top - p[1] - 10, x.removeClass("layerTipsB").addClass("layerTipsT").css({
                            "border-right-color": h.tipColor
                        })
                    }, function () {
                        h.tipLeft = h.left + h.width + 10, h.tipTop = h.top, x.removeClass("layerTipsL").addClass("layerTipsR").css({
                            "border-bottom-color": h.tipColor
                        })
                    }, function () {
                        h.autoLeft(), h.tipTop = h.top + h.height + 10, x.removeClass("layerTipsT").addClass("layerTipsB").css({
                            "border-right-color": h.tipColor
                        })
                    }, function () {
                        h.tipLeft = h.left - p[0] + 10, h.tipTop = h.top, x.removeClass("layerTipsR").addClass("layerTipsL").css({
                            "border-bottom-color": h.tipColor
                        })
                    }], h.where[a.tips.guide](), 0 === a.tips.guide ? h.top - (i.scrollTop() + p[1] + 16) < 0 && h.where[2]() : 1 === a.tips.guide ? i.width() - (h.left + h.width + p[0] + 16) > 0 || h.where[3]() : 2 === a.tips.guide ? h.top - i.scrollTop() + h.height + p[1] + 16 - i.height() > 0 && h.where[0]() : 3 === a.tips.guide ? p[0] + 16 - h.left > 0 && h.where[1]() : 4 === a.tips.guide, l.css({
                        left: h.tipLeft,
                        top: h.tipTop
                    })
            }
            a.fadeIn && (s.fade(l, a.fadeIn, 1), s.fade(o("#xubox_shade" + e), a.fadeIn, a.shade[0])), a.fix && "" === a.offset[0] && !a.shift && i.on("resize", function () {
                l.css({
                    top: (i.height() - l.outerHeight()) / 2
                })
            }), t.move()
        }, n.pt.shift = function (e, t, o) {
            var a = this,
                s = a.config,
                r = a.layerE,
                n = 0,
                l = i.width(),
                c = i.height() + (s.fix ? 0 : i.scrollTop());
            n = "50%" == s.offset[1] || "" == s.offset[1] ? r.outerWidth() / 2 : r.outerWidth();
            var f = {
                t: {
                    top: a.offsetTop
                },
                b: {
                    top: c - r.outerHeight() - s.border[0]
                },
                cl: n + s.border[0],
                ct: -r.outerHeight(),
                cr: l - n - s.border[0]
            };
            switch (e) {
                case "left-top":
                    r.css({
                        left: f.cl,
                        top: f.ct
                    }).animate(f.t, t);
                    break;
                case "top":
                    r.css({
                        top: f.ct
                    }).animate(f.t, t);
                    break;
                case "right-top":
                    r.css({
                        left: f.cr,
                        top: f.ct
                    }).animate(f.t, t);
                    break;
                case "right-bottom":
                    r.css({
                        left: f.cr,
                        top: c
                    }).animate(o ? f.t : f.b, t);
                    break;
                case "bottom":
                    r.css({
                        top: c
                    }).animate(o ? f.t : f.b, t);
                    break;
                case "left-bottom":
                    r.css({
                        left: f.cl,
                        top: c
                    }).animate(o ? f.t : f.b, t);
                    break;
                case "left":
                    r.css({
                        left: -r.outerWidth()
                    }).animate({
                        left: a.offsetLeft
                    }, t)
            }
        }, n.pt.autoArea = function (e) {
            var t, i = this,
                e = e || i.index,
                a = i.config,
                s = a.page,
                n = o("#" + r[0] + e),
                l = n.find(r[2]),
                c = n.find(r[5]),
                f = a.title ? l.innerHeight() : 0,
                d = 0;
            switch ("auto" === a.area[0] && c.outerWidth() >= a.maxWidth && n.css({
                width: a.maxWidth
            }), a.type) {
                case 0:
                    var p = n.find(".xubox_botton>a");
                    t = n.find(r[3]).outerHeight() + 20, p.length > 0 && (d = p.outerHeight() + 20);
                    break;
                case 1:
                    var u = n.find(r[4]);
                    t = o(s.dom).outerHeight(), "auto" === a.area[0] && n.css({
                        width: u.outerWidth()
                    }), ("" !== s.html || "" !== s.url) && (t = u.outerHeight());
                    break;
                case 2:
                    n.find("iframe").css({
                        width: n.outerWidth(),
                        height: n.outerHeight() - (a.title ? l.innerHeight() : 0)
                    });
                    break;
                case 3:
                    var h = n.find(".xubox_loading");
                    t = h.outerHeight(), c.css({
                        width: h.width()
                    })
            }
            "auto" === a.area[1] && c.css({
                height: f + t + d
            }), o("#xubox_border" + e).css({
                width: n.outerWidth() + 2 * a.border[0],
                height: n.outerHeight() + 2 * a.border[0]
            }), layer.ie6 && "auto" !== a.area[0] && c.css({
                width: n.outerWidth()
            }), n.css("50%" !== a.offset[1] && "" != a.offset[1] || 4 === a.type ? {
                marginLeft: 0
            } : {
                marginLeft: -n.outerWidth() / 2
            })
        }, n.pt.move = function () {
            var e = this,
                t = e.config,
                a = {
                    setY: 0,
                    moveLayer: function () {
                        if (0 == parseInt(a.layerE.css("margin-left"))) var e = parseInt(a.move.css("left"));
                        else var e = parseInt(a.move.css("left")) + -parseInt(a.layerE.css("margin-left"));
                        "fixed" !== a.layerE.css("position") && (e -= a.layerE.parent().offset().left, a.setY = 0), a.layerE.css({
                            left: e,
                            top: parseInt(a.move.css("top")) - a.setY
                        })
                    }
                },
                s = e.layerE.find(t.move);
            t.move && s.attr("move", "ok"), s.css(t.move ? {
                cursor: "move"
            } : {
                cursor: "auto"
            }), o(t.move).on("mousedown", function (e) {
                if (e.preventDefault(), "ok" === o(this).attr("move")) {
                    a.ismove = !0, a.layerE = o(this).parents("." + r[0]);
                    var s = a.layerE.offset().left,
                        n = a.layerE.offset().top,
                        l = a.layerE.width() - 6,
                        c = a.layerE.height() - 6;
                    o("#xubox_moves")[0] || o("body").append('<div id="xubox_moves" class="xubox_moves" style="left:' + s + "px; top:" + n + "px; width:" + l + "px; height:" + c + 'px; z-index:2147483584"></div>'), a.move = o("#xubox_moves"), t.moveType && a.move.css({
                        opacity: 0
                    }), a.moveX = e.pageX - a.move.position().left, a.moveY = e.pageY - a.move.position().top, "fixed" !== a.layerE.css("position") || (a.setY = i.scrollTop())
                }
            }), o(document).mousemove(function (e) {
                if (a.ismove) {
                    var o = e.pageX - a.moveX,
                        s = e.pageY - a.moveY;
                    if (e.preventDefault(), !t.moveOut) {
                        a.setY = i.scrollTop();
                        var r = i.width() - a.move.outerWidth() - t.border[0],
                            n = t.border[0] + a.setY;
                        o < t.border[0] && (o = t.border[0]), o > r && (o = r), n > s && (s = n), s > i.height() - a.move.outerHeight() - t.border[0] + a.setY && (s = i.height() - a.move.outerHeight() - t.border[0] + a.setY)
                    }
                    a.move.css({
                        left: o,
                        top: s
                    }), t.moveType && a.moveLayer(), o = null, s = null, r = null, n = null
                }
            }).mouseup(function () {
                try {
                    a.ismove && (a.moveLayer(), a.move.remove()), a.ismove = !1
                } catch (e) {
                    a.ismove = !1
                }
                t.moveEnd && t.moveEnd()
            })
        }, n.pt.autoclose = function () {
            var e = this,
                t = e.config.time,
                o = function () {
                    t--, 0 === t && (layer.close(e.index), clearInterval(e.autotime))
                };
            e.autotime = setInterval(o, 1e3)
        }, s.config = {
            end: {}
        }, n.pt.callback = function () {
            var e = this,
                t = e.layerE,
                i = e.config,
                a = i.dialog;
            e.openLayer(), e.config.success(t), layer.ie6 && e.IE6(t), t.find(".xubox_close").on("click", function () {
                i.close(e.index), layer.close(e.index)
            }), t.find(".xubox_yes").on("click", function () {
                i.yes ? i.yes(e.index) : a.yes(e.index)
            }), t.find(".xubox_no").on("click", function () {
                i.no ? i.no(e.index) : a.no(e.index), layer.close(e.index)
            }), e.config.shadeClose && o("#xubox_shade" + e.index).on("click", function () {
                layer.close(e.index)
            }), t.find(".xubox_min").on("click", function () {
                layer.min(e.index, i), i.min && i.min(t)
            }), t.find(".xubox_max").on("click", function () {
                o(this).hasClass("xubox_maxmin") ? (layer.restore(e.index), i.restore && i.restore(t)) : (layer.full(e.index, i), i.full && i.full(t))
            }), s.config.end[e.index] = i.end
        }, s.reselect = function () {
            o.each(o("select"), function () {
                var e = o(this);
                e.parents("." + r[0])[0] || 1 == e.attr("layer") && o("." + r[0]).length < 1 && e.removeAttr("layer").show(), e = null
            })
        }, n.pt.IE6 = function (e) {
            var t = this,
                a = e.offset().top;
            if (t.config.fix) var s = function () {
                e.css({
                    top: i.scrollTop() + a
                })
            };
            else var s = function () {
                e.css({
                    top: a
                })
            };
            s(), i.scroll(s), o.each(o("select"), function () {
                var e = o(this);
                e.parents("." + r[0])[0] || "none" == e.css("display") || e.attr({
                    layer: "1"
                }).hide(), e = null
            })
        }, n.pt.openLayer = function () {
            var e = this;
            e.layerE, layer.autoArea = function (t) {
                return e.autoArea(t)
            }, layer.shift = function (t, o, i) {
                e.shift(t, o, i)
            }, layer.setMove = function () {
                return e.move()
            }, layer.zIndex = e.config.zIndex, layer.setTop = function (e) {
                var t = function () {
                    layer.zIndex++, e.css("z-index", layer.zIndex + 1)
                };
                return layer.zIndex = parseInt(e[0].style.zIndex), e.on("mousedown", t), layer.zIndex
            }
        }, s.isauto = function (e, t, o) {
            "auto" === t.area[0] && (t.area[0] = e.outerWidth()), "auto" === t.area[1] && (t.area[1] = e.outerHeight()), e.attr({
                area: t.area + "," + o
            }), e.find(".xubox_max").addClass("xubox_maxmin")
        }, s.rescollbar = function (e) {
            r.html.attr("layer-full") == e && (r.html[0].style.removeProperty ? r.html[0].style.removeProperty("overflow") : r.html[0].style.removeAttribute("overflow"), r.html.removeAttr("layer-full"))
        }, layer.getIndex = function (e) {
            return o(e).parents("." + r[0]).attr("times")
        }, layer.getChildFrame = function (e, t) {
            return t = t || o("." + r[1]).parents("." + r[0]).attr("times"), o("#" + r[0] + t).find("." + r[1]).contents().find(e)
        }, layer.getFrameIndex = function (e) {
            return o(e ? "#" + e : "." + r[1]).parents("." + r[0]).attr("times")
        }, layer.iframeAuto = function (e) {
            e = e || o("." + r[1]).parents("." + r[0]).attr("times");
            var t = layer.getChildFrame("body", e).outerHeight(),
                i = o("#" + r[0] + e),
                a = i.find(r[2]),
                s = 0;
            a && (s = a.height()), i.css({
                height: t + s
            });
            var n = -parseInt(o("#xubox_border" + e).css("top"));
            o("#xubox_border" + e).css({
                height: t + 2 * n + s
            }), o("#" + r[1] + e).css({
                height: t
            })
        }, layer.iframeSrc = function (e, t) {
            o("#" + r[0] + e).find("iframe").attr("src", t)
        }, layer.area = function (e, t) {
            var i = [o("#" + r[0] + e), o("#xubox_border" + e)],
                a = i[0].attr("type"),
                n = i[0].find(r[5]),
                l = i[0].find(r[2]);
            if (a === s.type[1] || a === s.type[2]) {
                if (i[0].css(t), n.css({
                        width: t.width,
                        height: t.height
                    }), a === s.type[2]) {
                    var c = i[0].find("iframe");
                    c.css({
                        width: t.width,
                        height: l ? t.height - l.innerHeight() : t.height
                    })
                }
                "0px" !== i[0].css("margin-left") && (t.hasOwnProperty("top") && i[0].css({
                    top: t.top - (i[1][0] ? parseFloat(i[1].css("top")) : 0)
                }), t.hasOwnProperty("left") && i[0].css({
                    left: t.left + i[0].outerWidth() / 2 - (i[1][0] ? parseFloat(i[1].css("left")) : 0)
                }), i[0].css({
                    marginLeft: -i[0].outerWidth() / 2
                })), i[1][0] && i[1].css({
                    width: parseFloat(t.width) - 2 * parseFloat(i[1].css("left")),
                    height: parseFloat(t.height) - 2 * parseFloat(i[1].css("top"))
                })
            }
        }, layer.min = function (e, t) {
            var i = o("#" + r[0] + e),
                a = [i.position().top, i.position().left + parseFloat(i.css("margin-left"))];
            s.isauto(i, t, a), layer.area(e, {
                width: 180,
                height: 35
            }), i.find(".xubox_min").hide(), "page" === i.attr("type") && i.find(r[4]).hide(), s.rescollbar(e)
        }, layer.restore = function (e) {
            var t = o("#" + r[0] + e),
                i = t.attr("area").split(",");
            t.attr("type"), layer.area(e, {
                width: parseFloat(i[0]),
                height: parseFloat(i[1]),
                top: parseFloat(i[2]),
                left: parseFloat(i[3])
            }), t.find(".xubox_max").removeClass("xubox_maxmin"), t.find(".xubox_min").show(), "page" === t.attr("type") && t.find(r[4]).show(), s.rescollbar(e)
        }, layer.full = function (e, t) {
            var a, n = o("#" + r[0] + e),
                l = 2 * t.border[0] || 6,
                c = [n.position().top, n.position().left + parseFloat(n.css("margin-left"))];
            s.isauto(n, t, c), r.html.attr("layer-full") || r.html.css("overflow", "hidden").attr("layer-full", e), clearTimeout(a), a = setTimeout(function () {
                layer.area(e, {
                    top: "fixed" === n.css("position") ? 0 : i.scrollTop(),
                    left: "fixed" === n.css("position") ? 0 : i.scrollLeft(),
                    width: i.width() - l,
                    height: i.height() - l
                })
            }, 100)
        }, layer.title = function (e, t) {
            var i = o("#" + r[0] + (t || layer.index)).find(".xubox_title>em");
            i.html(e)
        }, layer.close = function (e) {
            var t = o("#" + r[0] + e),
                i = t.attr("type"),
                a = o("#xubox_moves, #xubox_shade" + e);
            if (t[0]) {
                if (i == s.type[1]) if (t.find(".xuboxPageHtml")[0]) t[0].innerHTML = "", t.remove();
                else {
                    t.find(".xubox_setwin,.xubox_close,.xubox_botton,.xubox_title,.xubox_border").remove();
                    for (var n = 0; 3 > n; n++) t.find(".layer_pageContent").unwrap().hide()
                } else t[0].innerHTML = "", t.remove();
                a.remove(), layer.ie6 && s.reselect(), s.rescollbar(e), "function" == typeof s.config.end[e] && s.config.end[e](), delete s.config.end[e]
            }
        }, layer.closeLoad = function () {
            layer.close(o(".xubox_loading").parents("." + r[0]).attr("times"))
        }, layer.closeTips = function () {
            layer.closeAll("tips")
        }, layer.closeAll = function (e) {
            o.each(o("." + r[0]), function () {
                var t = o(this),
                    i = e ? t.attr("type") === e : 1;
                i && layer.close(t.attr("times")), i = null
            })
        }, s.run = function () {
            o = jQuery, i = o(e), r.html = o("html"), o.layer = function (e) {
                var t = new n(e);
                return t.index
            }
        }, "function" == typeof define ? define(function () {
            return s.run(), layer
        }) : s.run()
    }(window);
var checkInviteCode = function (e, a) {
        var i = {
            code: e
        };
        ajax_req_sync("/user/isinvit", i, "GET", a)
    },
    getValidCode = function (e) {
        var a = {
            mobile: e
        };
        ajax_req("/user/getvalid", a, "GET")
    },
    checkValidCode = function (e, a) {
        var i = {
            code: e
        };
        ajax_req("/user/isvalid", i, "GET", a)
    },
    checkMobile = function (e, a) {
        var i = {
            mobile: e
        };
        ajax_req("/user/checkphone", i, "GET", a)
    },
    checkMail = function (e, a) {
        var i = {
            mail: e
        };
        ajax_req("/user/checkmail", i, "GET", a)
    },
    doRegist = function (e, a, i, o) {
        var n = {
            name: e,
            password: a,
            code: i
        };
        ajax_req("/useradd", n, "POST", o)
    },
    doRegistWithValidCode = function (e, a, i, o, n) {
        var t = {
            name: e,
            password: a,
            code: i,
            validCode: o
        };
        ajax_req("/useradd", t, "POST", n)
    },
    doLogin = function (e, a, i) {
        var o = {
            name: e,
            password: a
        };
        ajax_req("/login?v=" + Math.random(), o, "POST", i)
    };
$(function () {
    function getUrlParam(e) {
        var a = new RegExp("(^|&)" + e + "=([^&]*)(&|$)"),
            i = window.location.search.substr(1).match(a);
        return null != i ? unescape(i[2]) : ""
    }

    function goRegister() {
        $(".login-model,.forget-psw,#welcome").fadeOut("500", function () {
            $("#word_text,#register,.phone-email").fadeIn(500)
        }), $(".dotted-box").children().eq(1).addClass("dotted-stat-now").siblings().removeClass("dotted-stat-now")
    }

    function goLogin() {
        $("#word_text,#register,.phone-email").fadeOut("500", function () {
            $(".login-model,.forget-psw,#welcome").fadeIn(500)
        }), $(".dotted-box").children().eq(0).addClass("dotted-stat-now").siblings().removeClass("dotted-stat-now")
    }

    function checkPhone(e) {
        var a = $(e).val();
        if ($(e).parent().removeClass("input-box-error"), "" != a && "鎵嬫満鍙�" != a && isPhone(a)) {
            var i = function (a) {
                var i = a;
                i.isok ? $(".proving-send").removeClass("graybg-btn").removeAttr("disabled") : ($(e).parent().addClass("input-box-error"), $("#register .error-tips").html("Oops ! 姝ゆ墜鏈哄彿宸茶娉ㄥ唽浜�").fadeIn(500))
            };
            checkMobile(a, i)
        } else $(e).parent().addClass("input-box-error"), $("#register .error-tips").html("璇疯緭鍏ユ纭殑鎵嬫満鍙�").fadeIn(500)
    }

    function checkEmail(e) {
        var a = $(e).val();
        if ($(e).parent().removeClass("input-box-error"), "" != a && "閭" != a && isEmail(a)) {
            var i = function (a) {
                var i = a;
                i.isok ? $(".proving-send").removeClass("graybg-btn").removeAttr("disabled") : ($(e).parent().addClass("input-box-error"), $("#register .error-tips").html("Oops ! 姝ら偖绠卞凡琚敞鍐屼簡").fadeIn(500))
            };
            checkMail(a, i)
        } else $(e).parent().addClass("input-box-error"), $("#register .error-tips").html("璇疯緭鍏ユ纭殑閭").fadeIn(500)
    }

    function inviteCodeVerification(num) {
        var inviteCode = $("#invite_code").val(),
            vaild_callback = function (e) {
                var a = e;
                return a.isok ? (phone_email_flag = 3, void(panduan = num)) : ($("#invite_code").addClass("invitation-error"), $(".invite-error-prompt").fadeIn(500), !1)
            };
        if ("" == inviteCode || "锛堝彲閫夛級閭€璇风爜锛屽府Ta寰椾竴涓囧厓" == inviteCode) {
            var data = eval('({"isok":true})');
            vaild_callback(data)
        } else checkInviteCode(inviteCode, vaild_callback)
    }

    function validationR(e, a, i, o) {
        {
            var n = $(e).val(),
                t = $(a).val(),
                r = $("#register .error-tips"),
                s = $(i).val();
            $(o).val()
        }
        if ($("#pd_ep").val(n), 1 == phone_email_flag) {
            if ("" == n || "鎵嬫満鍙�" == n) return $(e).parent().addClass("input-box-error"), r.html("璇疯緭鍏ユ墜鏈哄彿").fadeIn(500), !1;
            if (!isPhone(n)) return $(e).parent().addClass("input-box-error"), r.html("鏍煎紡涓嶆纭�").fadeIn(500), !1;
            inviteCodeVerification(1)
        }
        if (2 == phone_email_flag) {
            if ("" == n || "閭" == n) return $(e).parent().addClass("input-box-error"), r.html("璇疯緭鍏ラ偖绠�").fadeIn(500), !1;
            if (!isEmail(n)) return $(e).parent().addClass("input-box-error"), r.html("鏍煎紡涓嶆纭�").fadeIn(500), !1;
            inviteCodeVerification(2)
        }
        if (3 == phone_email_flag) if ("" != t && "" != s) if (isPassword(t) && isPassword(s)) if (t != s) $(i).parent().addClass("input-box-error"), r.html("瀵嗙爜涓嶄竴鑷�").fadeIn(500);
        else if ($("#agreement").is(":checked")) {
            var l, d = function (e) {
                    var a = e;
                    if (a.isok) {
                        if (null != a.uid && null != a.name) {
                            var i = window._mvq || [];
                            window._mvq = i, i.push(["$setAccount", "m-161160-0"]), i.push(["$setGeneral", "registered", "", a.name, a.uid]), i.push(["$logConversion"]), !
                                function (e, i, o) {
                                    function n() {
                                        i.body ? (r = i.createElement("script"), r.src = u, i.body.insertBefore(r, i.body.firstChild)) : setTimeout(n(), 100)
                                    }

                                    var t, r, s = a.uid,
                                        l = location.href,
                                        d = i.referrer,
                                        c = i.cookie,
                                        p = c.match(/(^|;)\s*ipycookie=([^;]*)/),
                                        f = c.match(/(^|;)\s*ipysession=([^;]*)/);
                                    e.parent != e && (t = l, l = d, d = t), u = "//stats.ipinyou.com/cvt?a=" + o("7qs.vls.OuTTNmDI7cObJ-waTXDGyP") + "&c=" + o(p ? p[2] : "") + "&s=" + o(f ? f[2].match(/jump\%3D(\d+)/)[1] : "") + "&u=" + o(l) + "&r=" + o(d) + "&rd=" + (new Date).getTime() + "&OrderNo=" + o(s) + "&e=", n()
                                }(window, document, encodeURIComponent)
                        }
                        layer.msg("娉ㄥ唽鎴愬姛", 2, 1);
                        var o = $("#pd_ep").val(),
                            n = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                        if (n.test(o)) {
                            $.layer({
                                type: 1,
                                area: ["auto", "auto"],
                                border: [0],
                                title: "",
                                shade: [.8, "#000"],
                                closeBtn: [0, !1],
                                page: {
                                    dom: "#accountbind_phone"
                                }
                            })
                        } else {
                            var t = $("#backUrl").val(),
                                s = "bdcount=register";
                            if (t) {
                                var l = t.indexOf("?"),
                                    s = l && l > 0 ? "&" + s : "?" + s;
                                location.href = ctxBase + t + s
                            } else location.href = ctxBase + "/?" + s
                        }
                    } else r.html("娉ㄥ唽澶辫触").fadeIn(500)
                },
                c = $("#captcha").val(),
                p = function (e) {
                    var a = e;
                    if (a.isok) {
                        var i = $("#invite_code").val();
                        doRegistWithValidCode(n, t, i, c, d)
                    } else $("#captcha").parent().addClass("input-box-error"), $("#register .error-tips").html("楠岃瘉鐮佽緭鍏ユ湁璇�").fadeIn(500)
                };
            if (isEmail(n)) {
                var f = $("#captcha").val(),
                    v = $("#invite_code").val(),
                    h = $("#phone_email").val();
                l = layer.load("鍔犺浇涓€�"), $.ajax({
                    url: "/user/checkpwdcode",
                    type: "GET",
                    data: {
                        email: h,
                        code: f
                    },
                    async: !0
                }).done(function (e) {
                    e.isok ? (layer.close(l), doRegistWithValidCode(n, t, v, c, d)) : layer.msg("楠岃瘉鐮侀敊璇�", 1, 3)
                }).fail(function () {
                    layer.msg("楠岃瘉鐮侀敊璇�", 1, 3)
                })
            } else checkValidCode(c, p)
        } else r.html("璇峰悓鎰忕敤鎴峰崗璁�").fadeIn(500);
        else $(a).parent().addClass("input-box-error"), r.html("璇疯緭鍏�6-12浣嶆暟瀛楁垨瀛楁瘝").fadeIn(500);
        else $(a).parent().addClass("input-box-error"), r.html("璇疯緭鍏ュ瘑鐮�").fadeIn(500)
    }

    function validationL(e, a) {
        var i = $(e).val(),
            o = $(a).val(),
            n = $(".login-model .error-tips");
        if ("" != i && "鎵嬫満鍙�/閭" != i) if (isPhone(i) || isEmail(i)) if ("" != o) if (isPassword(o)) {
            var t = function (i) {
                var o = i;
                if (o.isok) {
                    var t = $("#backUrl").val();
                    location.href = t ? ctxBase + t : ctxBase + "/"
                } else $(e).parent().addClass("input-box-error"), $(a).parent().addClass("input-box-error"), n.html("Oops! 杈撳叆鐨勫笎鍙锋垨瀵嗙爜鏈夎").fadeIn(500)
            };
            doLogin(i, o, t)
        } else $(e).parent().addClass("input-box-error"), $(a).parent().addClass("input-box-error"), n.html("Oops! 杈撳叆鐨勫笎鍙锋垨瀵嗙爜鏈夎").fadeIn(500);
        else $(e).parent().addClass("input-box-error"), $(a).parent().addClass("input-box-error"), n.html("Oops! 杈撳叆鐨勫笎鍙锋垨瀵嗙爜鏈夎").fadeIn(500);
        else $(e).parent().addClass("input-box-error"), $(a).parent().addClass("input-box-error"), n.html("Oops! 杈撳叆鐨勫笎鍙锋垨瀵嗙爜鏈夎").fadeIn(500);
        else $(e).parent().addClass("input-box-error"), $(a).parent().addClass("input-box-error"), n.html("Oops! 杈撳叆鐨勫笎鍙锋垨瀵嗙爜鏈夎").fadeIn(500)
    }

    function times(e) {
        0 == wait ? (e.removeAttribute("disabled"), e.value = "閲嶅彂", $(e).removeClass("graybg-btn"), wait = 60) : (e.disabled = "true", e.value = "閲嶅彂(" + wait + ")", wait--, setTimeout(function () {
            times(e)
        }, 1e3))
    }

    var code = getUrlParam("code");
    code ? ($("#invite_code").val(code), goRegister()) : goLogin();
    var code_href = location.search;
    $(".register-btn").click(function () {
        location.href = "/h5/registe" + code_href
    }), $(".proving-send").click(function () {
        var e = $("#phone_email").val();
        $(this).addClass("graybg-btn"), 1 == phone_email_flag || 1 == panduan ? getValidCode(e) : (2 == phone_email_flag || 2 == panduan) && $.ajax({
            url: "/user/sendRegmail",
            type: "POST",
            data: {
                mail: e
            },
            async: !0
        }).done(function (e) {
            1 == e.isok ? layer.msg("鍙戦€佹垚鍔�", 1, 1) : 0 == e.isok && layer.msg("鍙戦€佸け璐�", 1, 3)
        }).fail(function () {
            layer.msg("鍙戦€佸け璐�", 1, 3)
        }), times(this)
    }), $("#follow_us").click(function (e) {
        e.stopPropagation(), $(".follow-us").fadeIn(300)
    }), $(".follow-us-btn").click(function () {
        $(".follow-us-box").hide(), $(this).find(".follow-us-box").show()
    }), $(document).click(function () {
        $(".follow-us").fadeOut(300), $(".follow-us-box").hide()
    }), $(".follow-us").click(function () {
        $(this).fadeIn(500)
    });
    var $wrapper = $(".login-wrapper"),
        $login = $(".login-model"),
        $register_box = $(".login-box"),
        $invitation = $("#invitation"),
        $register = $("#register"),
        $dotted = $(".dotted-box"),
        flag = !0;
    $("#register_btn").click(function () {
        goRegister()
    }), $(".goto-login").click(function () {
        goLogin()
    });
    var $phone_email = $("#phone_email"),
        phone_email_val = $("#phone_email").val(),
        phone_email_flag = 1,
        panduan = 0;
    $("#phone_email").val("鎵嬫満鍙�"), $(".switch-box").changeClass("switch-box-click"), $(".switch-box").on("click", function () {
        $("#register .error-tips").html("").hide(), $(".input-box").removeClass("input-box-error");
        var e = $(this).index();
        0 == e ? ($(".proving-box").show(), $("#phone_email").val("鎵嬫満鍙�"), phone_email_flag = 1) : ($(".proving-box").show(), $("#phone_email").val("閭"), phone_email_flag = 2)
    }), getUrlParam("back") && getUrlParam("back").indexOf("renren") > -1 && (goRegister(), $(".phone-email").find(".switch-box").last().click()), $phone_email.focus(function () {
        $(this).parent().hasClass("input-box-error") && ($(this).parent().removeClass("input-box-error"), $("#register .error-tips").html("").fadeOut(500)), ("閭" == $(this).val() || "鎵嬫満鍙�" == $(this).val()) && $(this).val("")
    }).blur(function () {
        1 == phone_email_flag && (checkPhone(this), "" == $(this).val() && $(this).val("鎵嬫満鍙�")), 2 == phone_email_flag && (checkEmail(this), "" == $(this).val() && $(this).val("閭"))
    }), $(".complete-btn").click(function () {
        $("#invite_code").hasClass("invitation-error") && ($("#invite_code").removeClass("invitation-error"), $(".invite-error-prompt").fadeOut(500), $("#invite_code").val("")), validationR("#phone_email", "#password", "#repassword", "#captcha")
    }), $("#agreement").click(function () {
        $(this).is(":checked") ? $("#register .error-tips").fadeOut(500) : $("#register .error-tips").html("璇峰悓鎰忕敤鎴峰崗璁�").fadeIn(500)
    }), $("#login_name").keyup(function () {
        var e = $("#login_psw").val();
        "" != e && $(".login-btn").addClass("login-btn-isok")
    }), $("#login_psw").keyup(function () {
        var e = $("#login_name").val();
        "" != e && "鎵嬫満鍙�/閭" != e && $(".login-btn").addClass("login-btn-isok")
    }), $(".login-btn").click(function () {
        validationL("#login_name", "#login_psw")
    }), $(document).on("keypress", "#login_psw", function (e) {
        "13" == e.keyCode && $(".login-btn").click()
    }), $(document).on("keypress", "#account,#phone_captcha,#new_repsw", function (e) {
        "13" == e.keyCode && $(".step-btn").click()
    }), $("#invite_code").focus(function () {
        $(this).removeClass("invitation-error"), $(".invite-error-prompt").fadeOut(500)
    });
    var wait = 60;
    $(".three-party-btn").click(function () {
        var e = location.search;
        window.location = "/3login/wxoauth" + e
    })
});

function ListHtml(e, i) {
    var t = $(e),
        n = $("<div></div>");
    n.html(e);
    var o = i ? n.find(i) : n.children(),
        r = o.length,
        a = {};
    try {
        this.$html = t, this.length = r, this.rows = o
    } catch (s) {
    }
    return a.$html = t, a.length = r, a.rows = o, a
}
function fomatFloat(e, i) {
    return i = i || 2, Math.round(e * Math.pow(10, i)) / Math.pow(10, i)
}
function getDesignerBannerHeight() {
    var e = $(window).width(),
        i = $(window).height() - 200;
    return 1550 / 630 > e / i && (i = Math.floor(e / (1550 / 630))), i
}
function toUtf8(e) {
    var i, t, n, o;
    for (i = "", n = e.length, t = 0; n > t; t++) o = e.charCodeAt(t), o >= 1 && 127 >= o ? i += e.charAt(t) : o > 2047 ? (i += String.fromCharCode(224 | o >> 12 & 15), i += String.fromCharCode(128 | o >> 6 & 63), i += String.fromCharCode(128 | o >> 0 & 63)) : (i += String.fromCharCode(192 | o >> 6 & 31), i += String.fromCharCode(128 | o >> 0 & 63));
    return i
}
function isName(e) {
    var i = /^[一-龥a-zA-Z0-9_]+$/;
    return i.test(e)
}

function isPhone(e) {
    var i = /^1[3|4|5|7|8]\d{9}$|(\(\d{3,4}\)|\d{3,4}-|\s)?\d{8}/;
    return i.test(e)
}
function isPassword(e) {
    var i = /^[\d_a-zA-Z]{6,12}$/;
    return i.test(e)
}
function openWeixin() {
    closeOpenWeixin();
    var e = '<div class="weixin-sharepop" >                                <div class="weixin-sharecode"><div id="wxeq"></div></div>                                <p>鐢ㄥ井淇℃壂涓€鎵紝灏嗙敓鎴愮殑椤甸潰鍒嗕韩鍑哄幓鍗冲彲</p>                            </div>';
    $(".weixin-sharepop").length < 1 && $("body").append(e), weixin_pop = $.layer({
        type: 1,
        area: ["auto", "auto"],
        border: [0],
        title: "",
        shadeClose: !0,
        closeBtn: [0, !1],
        page: {
            dom: ".weixin-sharepop"
        }
    })
}
function closeOpenWeixin() {
    $(".weixin-sharepop").remove(), layer.close(weixin_pop)
}
function openWeibo(e, i, t) {
    var n = "http://service.weibo.com/share/share.php?url=&appkey=&title=" + e + "&pic=" + i + "&ralateUid=&language=zh_cn";
    t.attr("href", n)
}
function shareWeibo(e, i, t, n) {
    var o = function (t) {
        var o = t,
            r = e + o.url;
        r = encodeURIComponent(r), openWeibo(r, i, n)
    };
    genshorturl(t, 1, o)
}
function shareWeiboFeed(e, i, t, n, o) {
    var r = function (n) {
        var r = n,
            a = e + r.url + i;
        a = encodeURIComponent(a), openWeibo(a, t, o)
    };
    genshorturl(n, 1, r)
}
function shareWeixin(e, i) {
    var t = function (i) {
        var t = i;
        genErCode(e, t.url)
    };
    genshorturl(i, 2, t), openWeixin()
}
function shareWeixinx(e, i, t) {
    var n = function (i) {
        var t = i;
        genErCode(e, t.url)
    };
    genshorturl(i, 2, n, t), openWeixin()
}
function shareWeixinNeedlogin(e, i) {
    weixinCallback = function () {
        var t = function (i) {
            var t = i;
            genErCode(e, t.url)
        };
        genshorturl(i, 2, t), openWeixin()
    }, F_isLogin(weixinCallback)
}
function isEmptyObj(e) {
    return null == e || "undefined" == typeof e || 0 == e.length
}
function genErCode(e, i) {
    $.ajax({
        url: "/islogin",
        type: "GET",
        dataType: "json",
        async: !1
    }).done(function (t) {
        t.isLogin ? (i.indexOf("?") >= 0 && (i = i + "&u=" + t.uid + "&fromweb=y"), showErCodeUrl(e, i)) : showErCodeUrl(e, i)
    })
}
function showErCodeUrl(e, i) {
    var t = toUtf8(i);
    $("#" + e).qrcode(document.createElement("canvas").getContext ? {
        width: 256,
        height: 256,
        text: t
    } : {
        render: "table",
        width: 256,
        height: 256,
        text: t
    })
}
function genErCodex(e, i, t, n) {
    var o = toUtf8(i);
    $("#" + e).qrcode(document.createElement("canvas").getContext ? {
        width: t,
        height: n,
        text: o
    } : {
        render: "table",
        width: t,
        height: n,
        text: o
    })
}
function getQueryString(e) {
    var i = new RegExp("(^|&)" + e + "=([^&]*)(&|$)", "i"),
        t = window.location.search.substr(1).match(i);
    return null != t ? unescape(t[2]) : ""
}
function getCookie(e) {
    return document.cookie.length > 0 && (c_start = document.cookie.indexOf(e + "="), -1 != c_start) ? (c_start = c_start + e.length + 1, c_end = document.cookie.indexOf(";", c_start), -1 == c_end && (c_end = document.cookie.length), unescape(document.cookie.substring(c_start, c_end))) : ""
}
function setCookie(e, i, t) {
    var n = new Date;
    n.setDate(n.getDate() + t), document.cookie = e + "=" + escape(i) + (null == t ? "" : ";expires=" + n.toGMTString())
}
Date.now || (Date.now = function () {
    return (new Date).valueOf()
}), $.ajaxSetup({
    timeout: 3e4,
    cache: !1
});
var Escape = {
    escape: function (e) {
        return String(e).replace(/&/g, "&amp;").replace(/"/g, "&quot;").replace(/'/g, "&#39;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
    },
    unescape: function (e) {
        return String(e).replace(/&amp;/g, "&").replace(/&quot;/g, '"').replace(/&#39;/g, "'").replace(/&lt;/g, "<").replace(/&gt;/g, ">")
    }
};
jQuery.fn.extend({
    autoHeight: function () {
        function e(e) {
            var i = jQuery(e);
            return i.css({
                height: i.attr("_initAdjustHeight"),
                "overflow-y": "hidden"
            }).height(e.scrollHeight)
        }

        return this.each(function () {
            var i = jQuery(this);
            i.attr("_initAdjustHeight") || i.attr("_initAdjustHeight", i.outerHeight()), e(this).on("paste cut keydown keyup focus blur", function () {
                e(this)
            })
        })
    }
});
var myInviteCode = "",
    isLogin = !1,
    width, height, goodsimgsHeight, findpsw_height, scroll_top, body_width, loadFunction = function () {
        width = $(window).width(), height = $(window).height(), body_width = getBodyWidth(), goodsimgsHeight = $(".goods-imgs").height(), $(".section").css({
            height: Number(height),
            "background-size": "cover"
        }), width >= 1100 ? scroll_top > Number(goodsimgsHeight) ? ($(".fastbar").show(), $(".quickbuy").addClass("quickbuy-fixed"), $(".quickbuy").closest("body").find("header").css("z-index", "-1").find(".logo").hide(), $(".item-quickbuy").closest("body").find("header").css("z-index", "-1").find(".logo").hide()) : ($(".fastbar").fadeOut(200), $(".quickbuy").removeClass("quickbuy-fixed"), $(".quickbuy").closest("body").find("header").css("z-index", "5").find(".logo").show(), $(".item-quickbuy").closest("body").find("header").css("z-index", "5").find(".logo").show()) : (scroll_top > Number(goodsimgsHeight) ? $(".fastbar").show() : $(".fastbar").fadeOut(200), $(".quickbuy").removeClass("quickbuy-fixed"), $(".quickbuy").closest("body").find("header").css("z-index", "5").find(".logo").show(), $(".item-quickbuy").closest("body").find("header").css("z-index", "5").find(".logo").show()), $(".userinfor-wrapper").css("min-height", height - 441);
        var e = $(".designer-banner").height();
        $(".designer-infor-box").css("height", .86 * e - 110), $(".designer-infor-layer").fadeIn(500), $(".team-members").css("height", width / 4), width > .67 * height ? height > width ? ($(".invite-wrapper").css("height", height), $(".invite-wrapper").css("background-size", "100% 100%")) : ($(".invite-wrapper").css("height", width), $(".invite-wrapper").css("background-size", "100% auto")) : ($(".invite-wrapper").css("height", height), $(".invite-wrapper").css("background-size", "auto 100%"));
        var i = $(".invite-content").height();
        $(".invite-footer,.invite-layer").css("height", i)
    },
    getBodyWidth = function () {
        return $("body").width()
    },
    middelFindPsw = function () {
        findpsw_height = parseInt($(".findpsw-wrapper").css("height")), $(".findpsw-wrapper").css("margin-top", -findpsw_height / 2 - 110), $(".success-wrapper").css("margin-top", -findpsw_height / 2 - 55)
    },
    preheatingBox = function () {
        var e = $(window).width() - 8;
        $(".preheating-box").css({
            width: e / 3,
            height: 4 * e / 15
        })
    };
$(window).resize(function () {
    loadFunction(), middelFindPsw(), preheatingBox()
}), $(window).load(function () {
    loadFunction(), middelFindPsw();
    var e = 0,
        i = 0;
    $("body").mousemove(function (t) {
        var n = t.pageX,
            o = t.pageY;
        0 == i && (i = n), 0 == e && (e = o), ml = n - i, ml = Math.ceil(ml / 50), $(".part-shop").css("left", -.1 * width - ml), mt = o - e, mt = Math.ceil(mt / 100), $(".part-shop").css("top", -.4 * height - mt)
    })
});
var isPC = function () {
    for (var e = navigator.userAgent, i = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"], t = !0, n = 0; n < i.length; n++) if (e.indexOf(i[n]) > 0) {
        t = !1;
        break
    }
    return t
};
$(function () {
    setTimeout(function () {
        preheatingBox()
    }, 200);
    var e = ".detail-contact-box,#btn_contactchat,.contact-btn,#btn_mechat,.icon-30-service,.icon-40-service,#lp_serverbtn,.lp-server,.lp-serverbtn,.server-fixed,.icon-50-service,.icon-70-service,.contact-service",
        i = $(e);
    $.each(i, function (e, i) {
        $(i).attr("href", "javascript:void(0)"), $(i).attr("target", "")
    }), $(document).on("click", e, function () {
        isPC() ? _MEIQIA("showPanel") : location.href = "https://static.meiqia.com/dist/standalone.html?eid=11197"
    }), width = $(window).width(), height = $(window).height(), $(".team-members").css("height", width / 4), $("input:text").each(function () {
        var e = $(this).val();
        $(this).hasClass("placeholder") ? ($(this).focus(function () {
            $(this).hide(), $(this).parent().find("input:password").show().focus()
        }), $(this).parent().find("input:password").focus(function () {
            $(this).parent().hasClass("input-box-error") && ($(".error-tips").fadeOut(500), $(this).parent().removeClass("input-box-error")), $(this).parent().find("input:text").hide()
        }).blur(function () {
            "" == $(this).val() && $(this).hide().parent().find("input:text").show()
        })) : $(this).focus(function () {
            $(this).parent().hasClass("input-box-error") && ($(this).parent().removeClass("input-box-error"), $(".error-tips").fadeOut(500)), e === $(this).val() && $(this).val("")
        }).blur(function () {
            "" == $(this).val() && $(this).val(e)
        })
    }), $("textarea").keydown(function (e) {
        return "13" == e.keyCode ? !1 : void 0
    }), $("input:password").bind("copy cut paste", function () {
        return !1
    }), $(window).scroll(function () {
        scroll_top = $(window).scrollTop(), scroll_top > 200 ? $(".back-top").fadeIn(500) : $(".back-top").fadeOut(500), width = $(window).width(), height = $(window).height(), goodsimgsHeight = $(".goods-imgs").height();
        var e = $(".banner-buybtn") && $(".banner-buybtn").length > 0 ? $(".banner-buybtn").offset().top + $(".banner-buybtn").height() - $(".fastbar").height() : goodsimgsHeight;
        width >= 1100 ? scroll_top > Number(e) ? ($(".fastbar").show(), $(".quickbuy").addClass("quickbuy-fixed"), $(".quickbuy").closest("body").find("header").css("z-index", "-1").find(".logo").hide(), $(".item-quickbuy").closest("body").find("header").css("z-index", "-1").find(".logo").hide()) : ($(".fastbar").fadeOut(200), $(".quickbuy").removeClass("quickbuy-fixed"), $(".quickbuy").closest("body").find("header").css("z-index", "5").find(".logo").show(), $(".item-quickbuy").closest("body").find("header").css("z-index", "5").find(".logo").show()) : (scroll_top > Number(e) ? $(".fastbar").show() : $(".fastbar").fadeOut(200), $(".quickbuy").removeClass("quickbuy-fixed"), $(".quickbuy").closest("body").find("header").css("z-index", "5").find(".logo").show(), $(".item-quickbuy").closest("body").find("header").css("z-index", "5").find(".logo").show()), height = $(window).height(), "5" == $("header").css("z-index") && "false" == $("header").attr("hover") && (scroll_top >= getDesignerBannerHeight() - 150 ? ($("header").addClass("preheating-header-black", 300, "easeOutSine"), $(".preheating-headlayer").hide(), $(".logo").hide(), $(".word-logo").show(), $(".index-sidebar").show()) : ($("header").removeClass("preheating-header-black", 200, "easeOutSine"), $(".preheating-headlayer").show(), $(".word-logo").hide(), $(".index-sidebar").hide(), $(".logo").show())), $("footer").length > 0 && ($("footer").offset().top - $(document).scrollTop() > 0 && $("footer").offset().top - $(document).scrollTop() < 445 ? ($("#confirm-order-btn").css("position", "absolute"), $(".quickbuy .icon-50").hide()) : ($("#confirm-order-btn").css("position", "fixed"), $(".quickbuy .icon-50").show()))
    }), $(".back-top,.icon-70-top").click(function () {
        $("html,body").animate({
            scrollTop: "0px"
        }, 500)
    }), $.ajax({
        url: "/sns/isnottakecoupon",
        type: "GET"
    }).done(function (e) {
        var i = e;
        i.isok ? $(".share-tooltips").show() : $(".share-tooltips").hide()
    }).fail(function () {
    });
    var t, n = setInterval(function () {
        $(".share-tooltips").stop().animate({
            left: "35px"
        }, {
            duration: 300,
            easing: "easeInOutSine"
        }).animate({
            left: "28px"
        }, {
            duration: 300,
            easing: "easeOutBounce"
        })
    }, 2e3);
    $(".share-list").hover(function () {
        var e = $(this);
        t = setTimeout(function () {
            e.find(".republic-share").stop().animate({
                opacity: "0"
            }, {
                duration: 200,
                easing: "easeInOutSine",
                complete: function () {
                    clearInterval(n), e.find(".share-tooltips").remove(), e.find(".icon-20-weixin").stop().animate({
                        left: "0px",
                        opacity: "1"
                    }, {
                        duration: 200,
                        easing: "easeInOutSine"
                    }), e.find(".icon-20-weibo").stop().animate({
                        left: "40px",
                        opacity: "1"
                    }, {
                        duration: 200,
                        easing: "easeInOutSine"
                    })
                }
            })
        }, 100)
    }, function () {
        clearTimeout(t), $(this).find(".icon-20-weixin").stop().animate({
            left: "20px",
            opacity: "0"
        }, {
            duration: 200,
            easing: "easeInOutSine"
        }), $(this).find(".icon-20-weibo").stop().animate({
            left: "20px",
            opacity: "0"
        }, {
            duration: 200,
            easing: "easeInOutSine",
            complete: function () {
                $(".republic-share").animate({
                    opacity: "1"
                }, {
                    duration: 200,
                    easing: "easeInOutSine"
                })
            }
        })
    }), $(".icon-header-mobile").click(function () {
        window.open("/e/ios/minisite")
    }), $(".icon-50-weixin-white").click(function () {
        var e = '<div class="weixin-sharepop">                                <div class="weixin-sharecode"><img src="/static/images/wap-footer-qrcode-4.jpg" /></div>                                <p>鐢ㄥ井淇℃壂涓€鎵紝鍏虫敞閫犱綔寰俊鍏紬鍙凤紝闅忔椂闅忓湴浣撻獙鐢熸椿缇庡</p>                            </div>';
        $(".weixin-sharepop").length < 1 && $("body").append(e), weixin_pop = $.layer({
            type: 1,
            shadeClose: !0,
            area: ["auto", "auto"],
            border: [0],
            title: "",
            closeBtn: [0, !1],
            page: {
                dom: ".weixin-sharepop"
            }
        })
    }), $("#step_weixin_share,.share-list .icon-20-weixin").click(function () {
        openWeixin();
        var e = _currentUrl;
        e += e.indexOf("?") >= 0 ? "&st=wx&s=hbbb" : "?st=wx&s=hccc", genErCode("wxeq", e)
    }), $(document).on("click", ".xubox_shade", function () {
        closeOpenWeixin()
    });
    "undefined" != typeof $(".menu-bar li").menuSlide && $(".menu-bar li").menuSlide(".menu-barli", ".menu-bar-line", 0, 300)
});

