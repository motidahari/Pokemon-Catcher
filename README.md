
![head](https://25.media.tumblr.com/a1e87d2030a73aee16661e8807da6c1d/tumblr_mkhnmmFwaA1rxvkeso1_500.gif)


## OOP EX2 - Pokemon Catcher:
This is the third assignment in the course opject oriented programming, ariel univerisy.
The project is about implementing a directed weithted graph and exploring some graph algorithms.
In the game we place several agent on a graph and their goal is to earn points by catching the random pokemons.
Each pokemon has its value.  
For more information, visit the project's wiki tab.

## made by:
> Eyal Levi - GitHub page: [github.com/LeviEyal](github.com/LeviEyal)  
> Moti Dahari - GitHub page: [github.com/motidahari](github.com/motidahari)

## How to use?
clone the project to your computer and run the file Ex2.jar by the command:  
`java -jar Ex2`  
on the same folder as Ex2.jar is on.  
the main window of the game will sow up:  
![enter image description here](https://i.paste.pics/8d6a502dc74bf155be1503eed6825784.png)  
Enter your ID and select the level you will to play and click the "play!" button.  

Another way to login to the game is by the command:  
`java -jar Ex2.jar 123456789 7`  
where 123456789 is your id and 7 is the level you want to play.  

## The package api
This package contains all the implementation of the graph.   
it represent the graph as adjacency list using hash maps.  
The class DWGraph_DS - Represent the graph implemented as adejency list.  
The class DWGraph_Algo - Contaions several algorithms that can be operate on a given graph.  
The class EdgeData - Represnts an edge in a graph.  
The class NodeData - Represnts a node in a graph.  
The class EdgeLocation - Represnts an edge's location in a graph.  
The class GeoLocation - Represnts a 3d location.  

The package files list:  

![img](https://i.paste.pics/5f95e39de017ccee8c03ba3cd987aef1.png)  
package diagram:  
![enter image description here](https://i.paste.pics/a8d3d2e83353cacd8c4e194380f84700.png)  

## The package gameClient  
The class Agent - a pokemon catcher. it has the following properties: position, id, speed, current edge, current pokemon, value.

The class Pokemon - Every pokemon has the following properties: edge (on which the pokemon on), value, type (if the type is -1 so the pokemon lies in a slope), position (3d point), minimum distance (every agent set this to mark the distances to all pokemons), from (the node on this pokemon's edge that "behind" the pokemon), to (the node on this pokemon's edge that "in front" of the pokemon), worth (based on the minimum distance + value)

The class Arena - represents a multi Agents Arena which move on a graph grabs Pokemons and earn points. This is where all the game is happening: the communication with the server, the agent and pokemons updating, the algorithms performs to manipulate the agents. etc.

The class Ex2 - This is the engine class of the game.

![img](https://i.paste.pics/55a1ddffc1ba282d8a18dd8a31683f2f.png)

## The package GUI
The class MyFrame - This class represents the main window of the game

The class MyPanel - This class represents the main panel containing the game

The class ComponentResizer  - allows to resize a component by dragging a border of the component.

![img](https://i.paste.pics/3aa139a8e89a9512d4f70cb083f083b1.png)

## Screeenshots
![enter image description here](https://i.paste.pics/8c4d6603dc096cc880bdcb3f72a546db.png)
![enter image description here](https://i.paste.pics/409c154b914c8923b082081188273f73.png)

## Algorithms used:
some variations of the BFS algorithm and dijkstra algorithms.

## External info
What is graph: [https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)](https://en.wikipedia.org/wiki/Graph_%28discrete_mathematics%29)

Connectivity of a graph: [https://en.wikipedia.org/wiki/Connectivity_(graph_theory)](https://en.wikipedia.org/wiki/Connectivity_%28graph_theory%29)

The shortest path problem: [https://en.wikipedia.org/wiki/Shortest_path_problem](https://en.wikipedia.org/wiki/Shortest_path_problem)

The BFS algorithm: [https://en.wikipedia.org/wiki/Breadth-first_search](https://en.wikipedia.org/wiki/Breadth-first_search)
