Rickshaw.namespace('Rickshaw.Graph.Legend');

Rickshaw.Graph.Legend = function(args) {

	var element = this.element = args.element;
	var graph = this.graph = args.graph;

	var self = this;

	element.classList.add('rickshaw_legend');
	
	var input = document.getElementById('search_input');

	var list = this.list = document.createElement('ul');
	list.setAttribute('id', 'search_list');
	input.appendChild(list);

	var series = graph.series
	.map( function(s) { return s } )

	if (!args.naturalOrder) {
		series = series.reverse();
	}

	this.lines = [];

	this.addLine = function (series) {
		
		// create a legend item
		var line = document.createElement('li');
		line.className = 'line';

		// give it a color
		var swatch = document.createElement('div');
		swatch.className = 'swatch';
		swatch.style.backgroundColor = series.color;
		line.appendChild(swatch);

		// label that will show up in legend
		var label = document.createElement('span');
		label.className = 'label';
		label.innerHTML = series.name;
		line.appendChild(label);		
		list.appendChild(line);

		// legend line corresponds to a series fo data
		line.series = series;

		//if (series.noLegend) {
		//	line.style.display = 'none';
		//}

		var _line = { element: line, series: series };
		if (self.shelving) {
			self.shelving.addAnchor(_line);
			self.shelving.updateBehaviour();
		}
		if (self.highlighter) {
			self.highlighter.addHighlightEvents(_line);
		}
		self.lines.push(_line);
	};

	// add a legend line for each data series
	series.forEach( function(s) {
		self.addLine(s);
	} );

	graph.onUpdate( function() {} );
};
