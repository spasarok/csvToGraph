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

	this.addLine = function (data, group) {
		
		var line = document.createElement('li');
		line.className = 'line';

		var swatch = document.createElement('div');
		swatch.className = 'swatch';
		swatch.style.backgroundColor = data.color;

		line.appendChild(swatch);

		var label = document.createElement('span');
		label.className = 'label';
		label.innerHTML = data.name;
		
		line.appendChild(label);		
		group.appendChild(line);

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
	

	var groups = ["ALL", "Men", "Women"];
	
	groups.forEach( function(g) {
		
		var group = document.createElement('li');
		group.className = 'group';
		group.id = "'" + g + "'";
		toplist.appendChild(group);
		
		var label = document.createElement('span');
		label.className = 'label';
		
		var content = document.createElement('div');
		content.className = 'content';
		content.innerHTML = g;
		label.appendChild(content);
		
		//label.innerHTML = g;
		
		group.appendChild(label);
		
		list = document.createElement('ul');
		list.className = g;
	    group.appendChild(list);
    	
		series.forEach( function(s) {
			if (s.name.search(g) == 0)
				self.addLine(s, list);
		});
	});


	//this.collapse = function(group) {
		//group.onClick( function {
			//if
		
//	}

	graph.onUpdate( function() {} );
};
