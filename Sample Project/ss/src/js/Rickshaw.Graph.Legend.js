Rickshaw.namespace('Rickshaw.Graph.Legend');

Rickshaw.Graph.Legend = function(args) {

	var element = this.element = args.element;
	var graph = this.graph = args.graph;

	var self = this;

	element.classList.add('rickshaw_legend');

	var toplist = this.toplist = document.createElement('ul');
	toplist.id = 'accordion';
	element.appendChild(toplist);

	var series = graph.series
	.map( function(s) { return s } )

	if (!args.naturalOrder) {
		series = series.reverse();
	}

	this.lines = [];

	this.addLine = function (data, list) {

		// create a legend item
		var line = document.createElement('li');
		line.className = 'line';
		line.style.display = 'none';

		// give it a color
		var swatch = document.createElement('div');
		swatch.className = 'swatch';
		swatch.style.backgroundColor = data.color;
		line.appendChild(swatch);

		// label that will show up in legend
		var label = document.createElement('span');
		label.className = 'label';
		label.innerHTML = data.name;
		line.appendChild(label);

		// add legend item to appropriate group		
		list.appendChild(line);

		// do stuff with the corresponding data
		line.series = data;

		if (data.noLegend) {
			line.style.display = 'none';
		}

		var _line = { element: line, series: data };
		if (self.shelving) {
			self.shelving.addAnchor(_line);
			self.shelving.updateBehaviour();
		}
		if (self.highlighter) {
			self.highlighter.addHighlightEvents(_line);
		}
		self.lines.push(_line);
	};

	//var groups = ["ALL", "Men", "Women"];
	var groups = ["ALL", "Men", "Women"];

	groups.forEach( function(g) {

		// label the group title in an li
		var group = document.createElement('li');
		group.className = 'group.enabled';
		group.id = g;
		toplist.appendChild(group);

		// indicate that the li is a label
		var label = document.createElement('span');
		label.className = 'label';

		// put the title content in a div
		var content = document.createElement('div');
		content.className = 'content';
		content.innerHTML = g;
		label.appendChild(content);
		group.appendChild(label);

		// create list to be shown under this group title
		list = document.createElement('ul');
		list.className = 'list';
		group.appendChild(list);
		
		// make list collapsible on click
		label.onclick = function() {
			
			var list = document.querySelectorAll('#' + g + ' > ul > li');
			
			// if the dropdown is open, collapse is
			if (label.className == 'group.enabled') {
				label.className = 'group.disabled';
				for (var i = 0; i < list.length; i++) 
					{ list[i].style.display = "none"; }

			}
			// otherwise, open it
			else {
				label.className = 'group.enabled';
				for (var i = 0; i < list.length; i++) 
					{ list[i].style.display = "block";}
			} 
		};
		
		// populate the list
		series.forEach( function(s) {
			if (s.name.search(g) == 0)
				self.addLine(s, list);
		});
	});

	graph.onUpdate( function() {} );
};
