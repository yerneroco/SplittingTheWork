import pygame
import os

# Define some colors
BLACK = (0, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)

# Set the dimensions of each square
SQUARE_SIZE = 50

# Prompt the user for the name of the input file
file_name = input("Enter the name of the input file: ")

# Load the data from the file
with open(file_name, 'r') as f:
    data = [[int(x) for x in line.split()] for line in f]

# Calculate the dimensions of the plane
num_rows = len(data)
num_cols = len(data[0])
plane_width = num_cols * SQUARE_SIZE
plane_height = num_rows * SQUARE_SIZE

# Initialize Pygame
pygame.init()

# Set the dimensions of the window
window_size = (plane_width, plane_height)
screen = pygame.display.set_mode(window_size)

# Draw the squares
for row in range(num_rows):
    for col in range(num_cols):
        square_color = GREEN if data[row][col] else BLUE
        square_rect = pygame.Rect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE)
        pygame.draw.rect(screen, square_color, square_rect)

# Save the image as a PNG file
png_name = os.path.splitext(file_name)[0] + '.png'
pygame.image.save(screen, png_name)

# Display the window
pygame.display.flip()

# Wait for the user to close the window
done = False
while not done:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            done = True

# Clean up
pygame.quit()
