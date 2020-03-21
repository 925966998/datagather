(function(factory) {
	if (typeof define === 'function' && define.amd) {
		define([ 'jquery' ], factory);
	} else {
		factory(jQuery);
	}
	;
}
		(function($) {
			$.lySelect = function(dom, settings) {
				var settings;
				var selectArray = new Array();
				var lySelect = {
					dom : {},
					api : {}
				};

				lySelect.init = function() {
					var _this = this;
					_this.settings = $
							.extend({}, $.lySelect.defaults, settings);
					this.selectChange(this, 0);
				};

				// 改变选择时的处理
				lySelect.selectChange = function(obj, level) {
					var _this = this;
					var c = "";
					var level=0;
					if ($(obj).is("select"))
						{
						c = $(obj).val();
						level=parseInt($(obj)
								.attr("level"));
						}
					if (c == undefined)
						c = "";
					
					var nextLevel = level + 1;					
					
					$("select[level]", $(dom)).each(
							function() {
								var l = parseInt($(this)
										.attr("level"));
								if (l >= nextLevel) {
									$(this).html("");
									$(this).hide("");
								}
							});
					if (_this.settings.valueInput)
						$("#" + _this.settings.valueInput).val(this.getSelectValue());
					if(c==""&&level>0)
						return;
					var url = this.settings.url + c;
					$.get(url,function(data) {
										var d = $(dom);
										if (data.items.length > 0) {
											var select = $("select[level='"
													+ nextLevel + "']", d);
											if (select.length == 0) {
												d.append("<select level='"
														+ nextLevel
														+ "'></select>");
												var select = $("select[level='"
														+ nextLevel + "']", d);
												select.on('change', function() {
													_this.selectChange(this,
															nextLevel);
												});
											}
											select.append("<option value='"
													+ _this.settings.firstValue
													+ "'>"
													+ _this.settings.firstTitle
													+ "</option>");

											for ( var i = 0; i < data.items.length; i++) {
												var item = data.items[i];
												select.append("<option value='"
														+ item.id + "'>"
														+ item.name
														+ "</option>");
											}
											select.show();
											if (_this.settings.initValue != null
													&& _this.settings.initValue != "") {
												if (c.length + 3 <= _this.settings.initValue.length) {
													select
															.val(_this.settings.initValue
																	.substr(
																			0,
																			c.length + 3));
													select.change();
												} else {
													_this.settings.initValue = "";
												}

											}
										} else {
											_this.settings.initValue = "";
										}
									});
					if(_this.settings.onChange!=null)
						_this.settings.onChange(c);
				};

				lySelect.getSelectValue = function() {
					var value = "";
					var d = $(dom);
					var _this = this;
					$("select[level]", d).each(function() {
						if ($(this).val() && $(this).val() != _this.settings.firstValue) {
							value = $(this).val();
						}
					});
					return value;
				};

				lySelect.init();

				return this;
			};

			// 默认值
			$.lySelect.defaults = {
				url : null, // 列表数据文件路径，或设为对象
				nodata : 'none', // 无数据状态
				required : false, // 是否为必选
				firstTitle : '请选择', // 第一个选项选项的标题
				firstValue : '', // 第一个选项的值
				initValue : '',
				valueInput : null,
				initValue : null,
				onChange:null
			};

			$.fn.lySelect = function(settings, callback) {
				this.each(function(i) {
					$.lySelect(this, settings, callback);
				});
				return this;
			};
		}));
