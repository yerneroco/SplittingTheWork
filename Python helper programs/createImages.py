import pygame
import os
import tkinter as tk
from tkinter import filedialog

# Define some colors
BLACK = (0, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

# Set the dimensions of each square
SQUARE_SIZE = 10

# Set the dimensions of the window
WINDOW_WIDTH = 1000
WINDOW_HEIGHT = 1000

# Prompt the user to select a directory
root = tk.Tk()
root.withdraw()
directory = filedialog.askdirectory()

# Get all text files in the directory
file_names = [file_name for file_name in os.listdir(directory) if file_name.endswith('.txt')]

# Initialize Pygame
pygame.init()

# Set the dimensions of the window
screen = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
clock = pygame.time.Clock()
current_file_index = 0



# Process every text file found in the directory
for file_name in os.listdir(directory):
    if file_name.endswith('.txt'):
        # Load the data from the file
        with open(os.path.join(directory, file_name), 'r') as f:
            data = [[int(x) for x in line.split()] for line in f]

        # Calculate the dimensions of the plane
        num_rows = len(data)
        num_cols = len(data[0])
        plane_width = num_cols * SQUARE_SIZE
        plane_height = num_rows * SQUARE_SIZE

        # Scale the squares to fit the window
        scale_factor = min(1, 800 / max(plane_width, plane_height))
        SQUARE_SIZE = int(SQUARE_SIZE * scale_factor)

        # Recalculate the dimensions of the plane
        plane_width = num_cols * SQUARE_SIZE
        plane_height = num_rows * SQUARE_SIZE



        # Draw the squares
        for row in range(num_rows):
            for col in range(num_cols):
                square_color = GREEN if data[row][col] else BLUE
                square_rect = pygame.Rect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE)
                pygame.draw.rect(screen, square_color, square_rect)

        # Save the image as a PNG file
        png_name = os.path.splitext(file_name)[0] + '.png'
        pygame.image.save(screen, os.path.join(directory, png_name))
 # Display the window
        pygame.display.flip()

quit = False
while not quit:
    # Load the data from the current file
    with open(os.path.join(directory, file_names[current_file_index]), 'r') as f:
        data = [[int(x) for x in line.split()] for line in f]

    # Calculate the dimensions of the plane
    num_rows = len(data)
    num_cols = len(data[0])

    # Calculate the max size of the plane to fit within the window
    max_plane_size = min(WINDOW_WIDTH, WINDOW_HEIGHT)
    max_rows = max_plane_size // SQUARE_SIZE
    max_cols = max_plane_size // SQUARE_SIZE

    # Calculate the starting position for the plane
    start_row = (WINDOW_HEIGHT - (max_rows * SQUARE_SIZE)) // 2
    start_col = (WINDOW_WIDTH - (max_cols * SQUARE_SIZE)) // 2

    # Draw the squares
    for row in range(max_rows):
        for col in range(max_cols):
            data_row = row + ((num_rows - max_rows) // 2)
            data_col = col + ((num_cols - max_cols) // 2)
            if 0 <= data_row < num_rows and 0 <= data_col < num_cols:
                square_color = GREEN if data[data_row][data_col] else BLUE
            else:
                square_color = BLACK
            square_rect = pygame.Rect(start_col + col * SQUARE_SIZE, start_row + row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE)
            pygame.draw.rect(screen, square_color, square_rect)

    # Update the display
    pygame.display.flip()

    # Wait for the user to close the window or switch files
    done = False
    while not done:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                done = True
                quit = True
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    current_file_index = (current_file_index - 1) % len(file_names)
                    done = True
                elif event.key == pygame.K_RIGHT:
                    current_file_index = (current_file_index + 1) % len(file_names)
                    done = True

    # Clear the screen for the next file
    screen.fill(BLACK)

# Clean up
pygame.quit()
