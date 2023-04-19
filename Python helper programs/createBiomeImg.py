import pygame
import os
import tkinter as tk
from tkinter import filedialog
# Define some colors
BLACK = (0, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
PLAINS = (57,71,41)
FOREST = (23,37,32)
DESERT = (98,60,14)


# Set the dimensions of the window
WINDOW_WIDTH = 1000
WINDOW_HEIGHT = 1000

# Prompt the user to select a directory
def get_directory():
    root = tk.Tk()
    root.withdraw()
    return filedialog.askdirectory()

# Get all text files in the directory
def get_text_files(directory):
    return [file_name for file_name in os.listdir(directory) if file_name.endswith('.txt')]

# Load the data from the file
def load_data(file_path):
    with open(file_path, 'r') as f:
        data = [[int(x) for x in line.split()] for line in f]
    return data

# Draw the squares for a given data and position
def draw_squares(data, screen, start_row, start_col, max_rows, max_cols, square_size):
    for row in range(max_rows):
        for col in range(max_cols):
            square_color = BLACK
            data_row = row + ((len(data) - max_rows) // 2)
            data_col = col + ((len(data[0]) - max_cols) // 2)
            if 0 <= data_row < len(data) and 0 <= data_col < len(data[0]):
                if data[data_row][data_col] == 0:
                    square_color = FOREST
                elif data[data_row][data_col] == 1:
                    square_color = PLAINS
                elif data[data_row][data_col] == 2:
                    square_color = DESERT
            square_rect = pygame.Rect(start_col + col * square_size, start_row + row * square_size, square_size, square_size)
            pygame.draw.rect(screen, square_color, square_rect)

# Save the image as a PNG file
def save_image(screen, directory, file_name):
    png_name = os.path.splitext(file_name)[0] + '.png'
    pygame.image.save(screen, os.path.join(directory, png_name))


def main():
    # Set the dimensions of each square
    SQUARE_SIZE = 10

    directory = get_directory()
    file_names = get_text_files(directory)
    # Initialize Pygame
    pygame.init()

    # Set the dimensions of the window
    screen = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
    clock = pygame.time.Clock()

    # Process every text file found in the directory and prints to image file
    for file_name in os.listdir(directory):
        if file_name.endswith('.txt'):
            # Load the data from the file
            data = load_data(os.path.join(directory, file_name))

            # Calculate the dimensions of the plane
            num_rows = len(data)
            num_cols = len(data[0])
            plane_width = num_cols * SQUARE_SIZE
            plane_height = num_rows * SQUARE_SIZE

            # Calculate the max size of the plane to fit within the window
            max_plane_size = min(WINDOW_WIDTH, WINDOW_HEIGHT)
            max_rows = max_plane_size // SQUARE_SIZE
            max_cols = max_plane_size // SQUARE_SIZE
            
             # Calculate the starting position for the plane
            start_row = (WINDOW_HEIGHT - (max_rows * SQUARE_SIZE)) // 2
            start_col = (WINDOW_WIDTH - (max_cols * SQUARE_SIZE)) // 2
            
            # Scale the squares to fit the window
            scale_factor = min(1, 800 / max(plane_width, plane_height))
            SQUARE_SIZE = int(SQUARE_SIZE * scale_factor)

            # Recalculate the dimensions of the plane
            plane_width = num_cols * SQUARE_SIZE
            plane_height = num_rows * SQUARE_SIZE

            draw_squares(data, screen, start_row, start_col, max_rows, max_cols, SQUARE_SIZE)

            # Save the image as a PNG file
            save_image(screen, directory, file_name)

    # Display the window
            pygame.display.flip()

    #Set current file index to 0 and quit to false
    current_file_index = 0  
    quit = False
    while not quit:
        # Load the data from the current file
        data = load_data(os.path.join(directory, file_names[current_file_index]))

        # Calculate the dimensions of the plane
        num_rows = len(data)
        num_cols = len(data[0])
        plane_width = num_cols * SQUARE_SIZE
        plane_height = num_rows * SQUARE_SIZE

        # Calculate the max size of the plane to fit within the window
        max_plane_size = min(WINDOW_WIDTH, WINDOW_HEIGHT)
        max_rows = max_plane_size // SQUARE_SIZE
        max_cols = max_plane_size // SQUARE_SIZE
        
            # Calculate the starting position for the plane
        start_row = (WINDOW_HEIGHT - (max_rows * SQUARE_SIZE)) // 2
        start_col = (WINDOW_WIDTH - (max_cols * SQUARE_SIZE)) // 2
        
        # Scale the squares to fit the window
        scale_factor = min(1, 800 / max(plane_width, plane_height))
        SQUARE_SIZE = int(SQUARE_SIZE * scale_factor)

        # Recalculate the dimensions of the plane
        plane_width = num_cols * SQUARE_SIZE
        plane_height = num_rows * SQUARE_SIZE

        draw_squares(data, screen, start_row, start_col, max_rows, max_cols, SQUARE_SIZE)

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
main()