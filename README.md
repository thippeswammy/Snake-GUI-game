# SnakeGUI
A classic Snake Game implemented in Java with a graphical user interface using NetBeans.

The main technique you're using in this Java code is building a simple Snake game using Swing for GUI components and event handling. Here are some key aspects:

1. **Swing Framework**: You're using Swing components like `JPanel`, `JFrame`, and `JButton` for creating the game interface and handling user interactions.

2. **Custom Painting**: You override the `paintComponent` method of `JPanel` to paint the game graphics directly onto the panel using a `Graphics` object. This allows you to create custom visuals for your game.

3. **Event Handling**: You implement event listeners (`ActionListener`) to handle user interactions with buttons and keyboard input. For example, you have separate classes (`EvtRestart` and `EvtExit`) for handling button click events.

4. **Game Logic**: You implement game logic such as moving the snake, generating apples, checking for collisions, and updating the game state accordingly. This logic is primarily handled in methods like `move`, `checkApple`, and `checkCollisions`.

5. **Timers**: You use a Swing `Timer` to create a game loop that triggers periodic updates to the game state. This is crucial for animating the snake's movement and updating the display.

6. **Randomization**: You utilize `java.util.Random` to generate random positions for the apples in the game.

Overall, the combination of Swing for GUI, custom painting, event handling, game logic, timers, and randomization forms the foundation of your Snake game implementation.
