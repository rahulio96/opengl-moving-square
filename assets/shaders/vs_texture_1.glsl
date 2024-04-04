
#version 430 core

// TODO: add layout information here
layout (location = 0) in vec3 aPosition;
layout (location = 1) in vec4 aColor;
layout (location = 2) in vec2 aTextureCoordinate;

uniform mat4 uProjMatrix;
uniform mat4 uViewMatrix;

// TODO: declare outputs of vs shader here:
out vec4 oColor;
out vec2 oTC;


void main()
{
    // TODO:  define the two outputs here:
    oColor = aColor;
    oTC = aTextureCoordinate;
    gl_Position = uProjMatrix * uViewMatrix * vec4(aPos, 1.0);
}
