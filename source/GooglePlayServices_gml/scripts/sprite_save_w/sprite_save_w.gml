
/// @function sprite_save_w(sprite, subimg, path)
function sprite_save_w(sprite, subimg, path) {

	//https://forum.yoyogames.com/index.php?threads/is-there-any-function-that-can-replace-sprite_save.38439/

	var q = sprite;
	var qw = sprite_get_width(q);
	var qh = sprite_get_height(q);
	var qx = sprite_get_xoffset(q);
	var qy = sprite_get_yoffset(q);
		
	var t = surface_create(qw, qh);
	surface_set_target(t);
	//draw_clear_alpha(c_black, 0);
	//gpu_set_blendmode(bm_add);
	draw_sprite(q, subimg, qx, qy);
	//gpu_set_blendmode(bm_normal);
	surface_reset_target();
	surface_save(t, path);
	surface_free(t);
}
