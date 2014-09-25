Rickshaw.namespace('Rickshaw.Graph.Legend');

Rickshaw.Graph.Legend = function(args) {

	varelement = this.element = args.element;
	var graph = this.graph = args.graph;

	var self = this;

	element.classList.add('rickshaw_legend');

	var toplist = this.list = document.createElement('ul');
	element.appendChild(toplist);
    // Set up the list we append individual series to.  We
    // change this below.
    var list = toplist;
    
	var series = graph.series
		.map( function(s) { return s } )
	// Alternately: series = graph.series.concat([]);

	if (!args.naturalOrder) {
		series = series.reverse();
	}

	this.lines = [];

	this.addLine = function (series) {
		
		var line = document.createElement('li');
		line.className = 'line';

		var swatch = document.createElement('div');
		swatch.className = 'swatch';
		swatch.style.backgroundColor = series.color;

		line.appendChild(swatch);

		var label = document.createElement('span');
		label.className = 'label';
		label.innerHTML = series.name;
		
		line.appendChild(label);		
		list.appendChild(line);

		line.series = series;

		if (series.noLegend) {
			line.style.display = 'none';
		}

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
	
	var groups = ["ALL", "Men", "Women"];

    groups.forEach( function(g) {
	    var group = document.createElement('li');
		group.className = 'group';
		group.id = "'" + g + "'";	
		//NEED TO APPEND
		var label = document.createElement('span');
		label.className = 'label';
		label.innerHTML = g;
		group.appendChild(label);
		list = document.createElement('ul');
	    group.appendChild(list);
	    
		series.forEach(function (s) {
			//if (s.name.search(g) >= 0
			if (series.name.search(g) >= 0)
				self.addLine(s);
		});	
		
		toplist.appendChild(group);
	}); 

	graph.onUpdate( function() {} );
};
