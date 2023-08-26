# Project "AnimalAbstractGame"

The "AnimalAbstractGame" project is an ecological system simulation where realistic interactions between animals and plants are modeled. The program creates a virtual world where animals can consume plants and each other, reproduce, move, and compete for survival.

## Key Features

- **Movement and Interaction**: Animals can interact with each other and with plants by moving across the virtual island. Interaction includes consumption, reproduction, and competition for resources.

- **Food Chains**: Animals have dietary preferences and can consume plants and other animals. Predators can hunt herbivores, adding realism to the ecosystem.

- **Reproduction**: Animals can reproduce, which leads to population growth. This process depends on the availability of breeding partners and is random.

- **Survival and Hunger**: Each animal has a hunger level that decreases over time without food. When the hunger level reaches a certain threshold, the animal can die of starvation.

- **Statistics and Output**: The game provides the ability to observe the dynamics of the ecological system through the output of information about objects on the map, hunger levels, reproduction, and other statistical indicators.

## Multithreading

The project employs multithreading principles to optimize the simulation. Simulation and statistics output occur in two separate threads:

- **Simulation**: One thread is responsible for simulating the ecological system. It calculates the state and interactions of objects, their movement, and reproduction. This approach achieves a realistic dynamic representation of object behavior in the virtual world.

- **Statistics Output**: Another thread is responsible for displaying statistics on the screen. The output intervals are configured to update information periodically. This helps observe the development of the ecosystem and changes in object populations.

This approach ensures more efficient use of computer resources and provides a more realistic representation of ecosystem dynamics.

## Usage

1. Clone the repository to your local environment.
2. Run the program.
3. Observe the dynamics of the ecosystem through the console interface.

## Configuration

Parameters of the ecosystem, such as dietary preferences, object placement, and more, can be adjusted in the project's configuration files. This allows making changes to the simulation and observing different scenarios of ecological system development.
