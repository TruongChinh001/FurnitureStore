/* JS Document */

/******************************

[Table of Contents]

1. Vars and Inits
2. Set Header
3. Init Custom Dropdown
4. Init Page Menu
5. Init Deals Slider
6. Init Tab Lines
7. Init Tabs
8. Init Featured Slider
9. Init Favorites
10. Init ZIndex
11. Init Popular Categories Slider
12. Init Banner 2 Slider
13. Init Arrivals Slider
14. Init Arrivals Slider ZIndex
15. Init Best Sellers Slider
16. Init Trends Slider
17. Init Reviews Slider
18. Init Recently Viewed Slider
19. Init Brands Slider
20. Init Timer

******************************/

$(document).ready(function () {
	initDealsSlider();
	initTabLines();
	initFeaturedSlider();
	featuredSliderZIndex();
	initPopularSlider();
	initViewedSlider();
	initBrandsSlider();
	initArrivalsSlider();
	initFavs();
	arrivalsSliderZIndex();
	bestsellersSlider();
	initPriceSlider();
	initIsotope();

	/* 

	5. Init Deals Slider

	*/

	function initDealsSlider() {
		if ($('.deals_slider').length) {
			var dealsSlider = $('.deals_slider');
			dealsSlider.owlCarousel(
				{
					items: 1,
					loop: false,
					navClass: ['deals_slider_prev', 'deals_slider_next'],
					nav: false,
					dots: false,
					smartSpeed: 1200,
					margin: 30,
					autoplay: false,
					autoplayTimeout: 5000
				});

			if ($('.deals_slider_prev').length) {
				var prev = $('.deals_slider_prev');
				prev.on('click', function () {
					dealsSlider.trigger('prev.owl.carousel');
				});
			}

			if ($('.deals_slider_next').length) {
				var next = $('.deals_slider_next');
				next.on('click', function () {
					dealsSlider.trigger('next.owl.carousel');
				});
			}
		}
	}

	/* 

	6. Init Tab Lines

	*/

	function initTabLines() {
		if ($('.tabs').length) {
			var tabs = $('.tabs');

			tabs.each(function () {
				var tabsItem = $(this);
				var tabsLine = tabsItem.find('.tabs_line span');
				var tabGroup = tabsItem.find('ul li');

				var posX = $(tabGroup[0]).position().left;
				tabsLine.css({ 'left': posX, 'width': $(tabGroup[0]).width() });
				tabGroup.each(function () {
					var tab = $(this);
					tab.on('click', function () {
						if (!tab.hasClass('active')) {
							tabGroup.removeClass('active');
							tab.toggleClass('active');
							var tabXPos = tab.position().left;
							var tabWidth = tab.width();
							tabsLine.css({ 'left': tabXPos, 'width': tabWidth });
						}
					});
				});
			});
		}
	}

	/* 

	7. Init Tabs

	*/

	function initTabs() {
		if ($('.tabbed_container').length) {
			//Handle tabs switching

			var tabsContainers = $('.tabbed_container');
			tabsContainers.each(function () {
				var tabContainer = $(this);
				var tabs = tabContainer.find('.tabs ul li');
				var panels = tabContainer.find('.panel');
				var sliders = panels.find('.slider');

				tabs.each(function () {
					var tab = $(this);
					tab.on('click', function () {
						panels.removeClass('active');
						var tabIndex = tabs.index(this);
						$($(panels[tabIndex]).addClass('active'));
						sliders.slick("unslick");
						sliders.each(function () {
							var slider = $(this);
							// slider.slick("unslick");
							if (slider.hasClass('bestsellers_slider')) {
								initBSSlider(slider);
							}
							if (slider.hasClass('featured_slider')) {
								initFSlider(slider);
							}
							if (slider.hasClass('arrivals_slider')) {
								initASlider(slider);
							}
						});
					});
				});
			});
		}
	}

	/* 

	8. Init Featured Slider

	*/

	function initFeaturedSlider() {
		if ($('.featured_slider').length) {
			var featuredSliders = $('.featured_slider');
			featuredSliders.each(function () {
				var featuredSlider = $(this);
				initFSlider(featuredSlider);
			});

		}
	}

	function initFSlider(fs) {
		var featuredSlider = fs;
		featuredSlider.on('init', function () {
			var activeItems = featuredSlider.find('.slick-slide.slick-active');
			for (var x = 0; x < activeItems.length - 1; x++) {
				var item = $(activeItems[x]);
				item.find('.border_active').removeClass('active');
				if (item.hasClass('slick-active')) {
					item.find('.border_active').addClass('active');
				}
			}
		}).on(
			{
				afterChange: function (event, slick, current_slide_index, next_slide_index) {
					var activeItems = featuredSlider.find('.slick-slide.slick-active');
					activeItems.find('.border_active').removeClass('active');
					for (var x = 0; x < activeItems.length - 1; x++) {
						var item = $(activeItems[x]);
						item.find('.border_active').removeClass('active');
						if (item.hasClass('slick-active')) {
							item.find('.border_active').addClass('active');
						}
					}
				}
			})
			.slick(
				{
					rows: 2,
					slidesToShow: 4,
					slidesToScroll: 4,
					infinite: false,
					arrows: false,
					dots: true,
					responsive:
						[
							{
								breakpoint: 768, settings:
								{
									rows: 2,
									slidesToShow: 3,
									slidesToScroll: 3,
									dots: true
								}
							},
							{
								breakpoint: 575, settings:
								{
									rows: 2,
									slidesToShow: 2,
									slidesToScroll: 2,
									dots: false
								}
							},
							{
								breakpoint: 480, settings:
								{
									rows: 1,
									slidesToShow: 1,
									slidesToScroll: 1,
									dots: false
								}
							}
						]
				});
	}

	/* 

	9. Init Favorites

	*/

	function initFavs() {
		// Handle Favorites
		var items = document.getElementsByClassName('product_fav');
		for (var x = 0; x < items.length; x++) {
			var item = items[x];
			item.addEventListener('click', function (fn) {
				fn.target.classList.toggle('active');
			});
		}
	}

	/* 

	10. Init ZIndex

	*/

	function featuredSliderZIndex() {
		// Hide slider dots on item hover
		var items = document.getElementsByClassName('featured_slider_item');

		for (var x = 0; x < items.length; x++) {
			var item = items[x];
			item.addEventListener('mouseenter', function () {
				$('.featured_slider .slick-dots').css('display', "none");
			});

			item.addEventListener('mouseleave', function () {
				$('.featured_slider .slick-dots').css('display', "block");
			});
		}
	}

	/* 

	11. Init Popular Categories Slider

	*/

	function initPopularSlider() {
		if ($('.popular_categories_slider').length) {
			var popularSlider = $('.popular_categories_slider');

			popularSlider.owlCarousel(
				{
					loop: true,
					autoplay: false,
					nav: false,
					dots: false,
					responsive:
					{
						0: { items: 1 },
						575: { items: 2 },
						640: { items: 3 },
						768: { items: 4 },
						991: { items: 5 }
					}
				});

			if ($('.popular_categories_prev').length) {
				var prev = $('.popular_categories_prev');
				prev.on('click', function () {
					popularSlider.trigger('prev.owl.carousel');
				});
			}

			if ($('.popular_categories_next').length) {
				var next = $('.popular_categories_next');
				next.on('click', function () {
					popularSlider.trigger('next.owl.carousel');
				});
			}
		}
	}


	/* 

	13. Init Arrivals Slider

	*/

	function initArrivalsSlider() {
		if ($('.arrivals_slider').length) {
			var arrivalsSliders = $('.arrivals_slider');
			arrivalsSliders.each(function () {
				var arrivalsSlider = $(this);
				initASlider(arrivalsSlider);
			});

		}
	}

	function initASlider(as) {
		var arrivalsSlider = as;
		arrivalsSlider.on('init', function () {
			var activeItems = arrivalsSlider.find('.slick-slide.slick-active');
			for (var x = 0; x < activeItems.length - 1; x++) {
				var item = $(activeItems[x]);
				item.find('.border_active').removeClass('active');
				if (item.hasClass('slick-active')) {
					item.find('.border_active').addClass('active');
				}
			}
		}).on(
			{
				afterChange: function (event, slick, current_slide_index, next_slide_index) {
					var activeItems = arrivalsSlider.find('.slick-slide.slick-active');
					activeItems.find('.border_active').removeClass('active');
					for (var x = 0; x < activeItems.length - 1; x++) {
						var item = $(activeItems[x]);
						item.find('.border_active').removeClass('active');
						if (item.hasClass('slick-active')) {
							item.find('.border_active').addClass('active');
						}
					}
				}
			})
			.slick(
				{
					rows: 2,
					slidesToShow: 5,
					slidesToScroll: 5,
					infinite: false,
					arrows: false,
					dots: true,
					responsive:
						[
							{
								breakpoint: 768, settings:
								{
									rows: 2,
									slidesToShow: 3,
									slidesToScroll: 3,
									dots: true
								}
							},
							{
								breakpoint: 575, settings:
								{
									rows: 2,
									slidesToShow: 2,
									slidesToScroll: 2,
									dots: false
								}
							},
							{
								breakpoint: 480, settings:
								{
									rows: 1,
									slidesToShow: 1,
									slidesToScroll: 1,
									dots: false
								}
							}
						]
				});
	}

	/* 

	14. Init Arrivals Slider ZIndex

	*/

	function arrivalsSliderZIndex() {
		// Hide slider dots on item hover
		var items = document.getElementsByClassName('arrivals_slider_item');

		for (var x = 0; x < items.length; x++) {
			var item = items[x];
			item.addEventListener('mouseenter', function () {
				$('.arrivals_slider .slick-dots').css('display', "none");
			});

			item.addEventListener('mouseleave', function () {
				$('.arrivals_slider .slick-dots').css('display', "block");
			});
		}
	}

	/* 

	15. Init Best Sellers Slider

	*/

	function bestsellersSlider() {
		if ($('.bestsellers_slider').length) {
			var bestsellersSliders = $('.bestsellers_slider');
			bestsellersSliders.each(function () {
				var bestsellersSlider = $(this);

				initBSSlider(bestsellersSlider);
			})
		}
	}

	function initBSSlider(bss) {
		var bestsellersSlider = bss;

		bestsellersSlider.slick(
			{
				rows: 2,
				infinite: true,
				slidesToShow: 3,
				slidesToScroll: 3,
				arrows: false,
				dots: true,
				autoplay: true,
				autoplaySpeed: 6000,
				responsive:
					[
						{
							breakpoint: 1199, settings:
							{
								rows: 2,
								slidesToShow: 2,
								slidesToScroll: 2,
								dots: true
							}
						},
						{
							breakpoint: 991, settings:
							{
								rows: 2,
								slidesToShow: 1,
								slidesToScroll: 1,
								dots: true
							}
						},
						{
							breakpoint: 575, settings:
							{
								rows: 1,
								slidesToShow: 1,
								slidesToScroll: 1,
								dots: false
							}
						}
					]
			});
	}


	/* 
    
		18. Init Recently Viewed Slider
    
		*/


	function initViewedSlider() {
		if ($('.viewed_slider').length) {
			var viewedSlider = $('.viewed_slider');

			viewedSlider.owlCarousel(
				{
					loop: true,
					margin: 30,
					autoplay: true,
					autoplayTimeout: 3000,
					nav: false,
					dots: false,
					responsive:
					{
						0: { items: 1 },
						575: { items: 2 },
						768: { items: 3 },
						991: { items: 4 },
						1199: { items: 6 }
					}
				});

			if ($('.viewed_prev').length) {
				var prev = $('.viewed_prev');
				prev.on('click', function () {
					viewedSlider.trigger('prev.owl.carousel');
				});
			}

			if ($('.viewed_next').length) {
				var next = $('.viewed_next');
				next.on('click', function () {
					viewedSlider.trigger('next.owl.carousel');
				});
			}
		}
	}

	/* 
   
	   19. Init Brands Slider
   
	   */

	function initBrandsSlider() {
		if ($('.brands_slider').length) {
			var brandsSlider = $('.brands_slider');

			brandsSlider.owlCarousel(
				{
					loop: true,
					autoplay: true,
					autoplayTimeout: 5000,
					nav: false,
					dots: false,
					autoWidth: true,
					items: 8,
					margin: 42
				});

			if ($('.brands_prev').length) {
				var prev = $('.brands_prev');
				prev.on('click', function () {
					brandsSlider.trigger('prev.owl.carousel');
				});
			}

			if ($('.brands_next').length) {
				var next = $('.brands_next');
				next.on('click', function () {
					brandsSlider.trigger('next.owl.carousel');
				});
			}
		}
	}

	/* 

	20. Init Isotope

	*/

	function initIsotope() {
		var sortingButtons = $('.shop_sorting_button');

		$('.product_grid').isotope({
			itemSelector: '.product_item',
			getSortData: {
				price_asc: function (itemElement) {
					var priceEle = $(itemElement).find('.product_price').text().replace('$', '');
					return parseFloat(priceEle);
				},
				price_desc: function (itemElement) {
					var priceEle = $(itemElement).find('.product_price').text().replace('$', '');
					return parseFloat(priceEle);
				},
				name: '.product_name div a'
			},
			animationOptions: {
				duration: 750,
				easing: 'linear',
				queue: false
			}
		});

		// Sort based on the value from the sorting_type dropdown
		sortingButtons.each(function () {
			$(this).on('click', function () {
				$('.sorting_text').text($(this).text());
				var option = $(this).attr('data-isotope-option');
				option = JSON.parse(option);
				$('.product_grid').isotope(option);
			});
		});

	}

	/* 
   
	   21. Init Price Slider
   
	   */

	function initPriceSlider() {
		if ($("#slider-range").length) {
			$("#slider-range").slider(
				{
					range: true,
					min: 0,
					max: 1000,
					values: [0, 580],
					slide: function (event, ui) {
						$("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
					}
				});

			$("#amount").val("$" + $("#slider-range").slider("values", 0) + " - $" + $("#slider-range").slider("values", 1));
			$('.ui-slider-handle').on('mouseup', function () {
				$('.product_grid').isotope({
					filter: function () {
						var priceRange = $('#amount').val();
						var priceMin = parseFloat(priceRange.split('-')[0].replace('$', ''));
						var priceMax = parseFloat(priceRange.split('-')[1].replace('$', ''));
						var itemPrice = $(this).find('.product_price').clone().children().remove().end().text().replace('$', '');

						return (itemPrice > priceMin) && (itemPrice < priceMax);
					},
					animationOptions: {
						duration: 750,
						easing: 'linear',
						queue: false
					}
				});
			});
		}
	}

});


//custom
/*
Template Name: Material Pro Admin
Author: Themedesigner
Email: niravjoshi87@gmail.com
File: js
*/
$(function () {
	"use strict";
	$(function () {
		$(".preloader").fadeOut();
	});

	// ============================================================== 
	// This is for the top header part and sidebar part
	// ==============================================================  
	var set = function () {
		var width = (window.innerWidth > 0) ? window.innerWidth : this.screen.width;
		var topOffset = 70;
		if (width < 1170) {
			$("body").addClass("mini-sidebar");
			$('.navbar-brand span').hide();
			$(".scroll-sidebar, .slimScrollDiv").css("overflow-x", "visible").parent().css("overflow", "visible");
			$(".sidebartoggler i").addClass("ti-menu");
		} else {
			$("body").removeClass("mini-sidebar");
			$('.navbar-brand span').show();
			//$(".sidebartoggler i").removeClass("ti-menu");
		}

		var height = ((window.innerHeight > 0) ? window.innerHeight : this.screen.height) - 1;
		height = height - topOffset;
		if (height < 1) height = 1;
		if (height > topOffset) {
			$(".page-wrapper").css("min-height", (height) + "px");
		}
	};
	$(window).ready(set);
	$(window).on("resize", set);

	// topbar stickey on scroll
	$(".fix-header .topbar").stick_in_parent({});

	// this is for close icon when navigation open in mobile view
	$(".nav-toggler").click(function () {
		$("body").toggleClass("show-sidebar");
		$(".nav-toggler i").toggleClass("ti-menu");
		$(".nav-toggler i").addClass("ti-close");
	});
	$(".sidebartoggler").on('click', function () {
		//$(".sidebartoggler i").toggleClass("ti-menu");
	});
	$(".search-box a, .search-box .app-search .srh-btn").on('click', function () {
		$(".app-search").toggle(200);
	});

	// ============================================================== 
	// Auto select left navbar
	// ============================================================== 
	$(function () {
		var url = window.location;
		var element = $('ul#sidebarnav a').filter(function () {
			return this.href == url;
		}).addClass('active').parent().addClass('active');
		while (true) {
			if (element.is('li')) {
				element = element.parent().addClass('in').parent().addClass('active');
			} else {
				break;
			}
		}
	});
	// ============================================================== 
	//tooltip
	// ============================================================== 
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	})
	// ============================================================== 
	// Sidebarmenu
	// ============================================================== 
	$(function () {
		$('#sidebarnav').metisMenu();
	});
	// ============================================================== 
	// Slimscrollbars
	// ============================================================== 
	$('.scroll-sidebar').slimScroll({
		position: 'left',
		size: "5px",
		height: '100%',
		color: '#dcdcdc'
	});
	// ============================================================== 
	// Resize all elements
	// ============================================================== 
	$("body").trigger("resize");
});

jQuery(function ($) {

	$(".sidebar-dropdown > a").click(function () {
		$(".sidebar-submenu").slideUp(200);
		if (
			$(this)
				.parent()
				.hasClass("active")
		) {
			$(".sidebar-dropdown").removeClass("active");
			$(this)
				.parent()
				.removeClass("active");
		} else {
			$(".sidebar-dropdown").removeClass("active");
			$(this)
				.next(".sidebar-submenu")
				.slideDown(200);
			$(this)
				.parent()
				.addClass("active");
		}
	});

	$("#close-sidebar").click(function () {
		$(".shop").removeClass("toggled");
	});
	$("#show-sidebar").click(function () {
		$(".shop").addClass("toggled");
	});
});


