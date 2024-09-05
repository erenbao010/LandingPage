(function() {

	"use strict";

	AOS.init({
		ease: 'slide',
		once: true
	});

	var slider = function(){

		var carouselCourses = document.querySelectorAll('.carousel-courses');
		if ( carouselCourses.length > 0 ) {

			var coursesSlider = tns({
				container: '.carousel-courses',
				items: 1,
				mode: 'carousel',
				autoplay: false,
			  	animateIn: 'tns-fadeIn',
		    	animateOut: 'tns-fadeOut',
				speed: 700,
				nav: true,
				gutter: 20,
				controls: false,
				autoHeight: true,
				autoplayButtonOutput: false,
				prevButton: document.querySelector('.prev-btn'),
				nextButton: document.querySelector('.next-btn'),
				responsive:{
					0:{
						items: 1,
						gutter: 0
					},
					600:{
						items: 2,
						gutter: 20
					},
					1000:{
						items: 4,
						gutter: 20
					}
				}
			});

		}
		var onlineCourses = document.querySelectorAll('.online-course');
		if ( onlineCourses.length > 0 ) {
			onlineCourses.forEach(onlineCourse=>{
				var  onlineCoursesSlider = tns({
					container: onlineCourse,
					items: 4,
					mode: 'carousel',
					autoplay: false,
					animateIn: 'tns-fadeIn',
					animateOut: 'tns-fadeOut',
					speed: 700,
					nav: true,
					gutter: 20,
					controls: true,
					prevButton: onlineCourse.parentNode.querySelector('.prev-btn'),
					nextButton: onlineCourse.parentNode.querySelector('.next-btn'),
					responsive:{
						0:{
							items: 1,
							gutter: 0
						},
						600:{
							items: 2,
							gutter: 20
						},
						1000:{
							items: 4,
							gutter: 20
						}
					}
				});
			});
			

		}

		var carouselSlider = document.querySelectorAll('.carousel-testimony');
		if ( carouselSlider.length > 0 ) {

			var testimonySlider = tns({
				container: '.carousel-testimony',
				items: 1,
				mode: 'carousel',
				autoplay: true,
			  animateIn: 'tns-fadeIn',
		    animateOut: 'tns-fadeOut',
				speed: 700,
				nav: true,
				gutter: 20,
				controls: false,
				autoHeight: true,
				autoplayButtonOutput: false,
				responsive:{
					0:{
						items: 1,
						gutter: 0
					},
					600:{
						items: 2,
						gutter: 20
					},
					1000:{
						items: 4,
						gutter: 20
					}
				}
			});

		}

	}
	slider();
	
	//COUNTER
	'use trict';
		// How long you want the animation to take, in ms
		const animationDuration = 2000;
		// Calculate how long each ‘frame’ should last if we want to update the animation 60 times per second
		const frameDuration = 1000 / 60;
		// Use that to calculate how many frames we need to complete the animation
		const totalFrames = Math.round( animationDuration / frameDuration );
		// An ease-out function that slows the count as it progresses
		const easeOutQuad = t => t * ( 2 - t );


		const numberWithCommas = n => {
			return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		}

		// The animation function, which takes an Element
		const animateCountUp = el => {
			let frame = 0;
			const countTo = parseInt( el.innerHTML, 10 );
			// Start the animation running 60 times per second
			const counter = setInterval( () => {
			frame++;
			// Calculate our progress as a value between 0 and 1
			// Pass that value to our easing function to get our
			// progress on a curve
			const progress = easeOutQuad( frame / totalFrames );
			// Use the progress value to calculate the current count
			const currentCount = Math.round( countTo * progress );

			// If the current count has changed, update the element
			if ( parseInt( el.innerHTML, 10 ) !== currentCount ) {
			el.innerHTML = numberWithCommas(currentCount);
		}

		// If we’ve reached our last frame, stop the animation
		if ( frame === totalFrames ) {
			clearInterval( counter );
		}
		}, frameDuration );
		};

		// Run the animation on all elements with a class of ‘countup’
		const runAnimations = () => {
			const countupEls = document.querySelectorAll( '.countup' );
			countupEls.forEach( animateCountUp );
		};




		// In Viewed
		var elements;
		var windowHeight;

		function init() {
			elements = document.querySelectorAll('.section-counter');
			windowHeight = window.innerHeight;
		}

		function checkPosition() {
			var i;
			for (i = 0; i < elements.length; i++) {
				var element = elements[i];
				var positionFromTop = elements[i].getBoundingClientRect().top;
			if (positionFromTop - windowHeight <= 0) {
			if( !element.classList.contains('viewed') ) {
			element.classList.add('viewed');
			runAnimations();
			} else {
			if ( element.classList.contains('viewed') ) {

			}
		}

		}
		}
		}

		window.addEventListener('scroll', checkPosition);
		window.addEventListener('resize', init);

		init();
		checkPosition();


	//GLIGHTBOX
	const lightbox = GLightbox({
	  touchNavigation: true,
	  loop: true,
	  autoplayVideos: true
	});

	// img click block
	var stopFunc = function(e) { e.preventDefault(); e.stopPropagation(); return false; };
	var all = document.querySelectorAll('img');
	for (var idx in all) {
		var el = all[idx];
		if (el.addEventListener) {
			el.addEventListener('click', stopFunc, true); // have to true
		}
	}
	const width = 50;
	const space1 = 35;
	const space = 40;

	var items = document.querySelectorAll('.mapedu .circle .itemMenuBox');

		for(var i = 0, l = items.length; i < l; i++) {
		items[i].style.left = (width - space1*Math.cos(-0.5 * Math.PI - 2*(1/l)*i*Math.PI)).toFixed(4) + "%";
		
		items[i].style.top = (width + space1*Math.sin(-0.5 * Math.PI - 2*(1/l)*i*Math.PI)).toFixed(4) + "%";
		}
	var itemICT = document.querySelectorAll('.mapICT .circle .itemMenuBox');

		for(var i = 0, l = itemICT.length; i < l; i++) {
		itemICT[i].style.left = (width - space*Math.cos(-0.5 * Math.PI - 2*(1/l)*i*Math.PI)).toFixed(4) + "%";
		
		itemICT[i].style.top = (width + space*Math.sin(-0.5 * Math.PI - 2*(1/l)*i*Math.PI)).toFixed(4) + "%";
		}
})()

